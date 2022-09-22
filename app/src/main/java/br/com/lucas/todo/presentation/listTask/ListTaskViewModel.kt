package br.com.lucas.todo.presentation.listTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.todo.data.Task
import br.com.lucas.todo.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(private val taskDao: TaskDao) : ViewModel() {

    val taskList = MutableLiveData<List<Task>>()

    fun isTaskListEmpty(): Boolean? {
        return taskList.value?.isEmpty()
    }

    fun delete(task: Task, toast: () -> Unit) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
            refreshScreen()
            toast()
        }
    }

    fun refreshScreen() {
        viewModelScope.launch {
            val tasks = taskDao.getAllTasks()
            val sortedTasks = tasks.sortedWith(compareBy({ it.taskDate }, { it.taskTime }))
            taskList.postValue(
                sortedTasks
            )
        }
    }
}