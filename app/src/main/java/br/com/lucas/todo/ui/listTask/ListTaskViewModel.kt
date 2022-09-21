package br.com.lucas.todo.ui.listTask

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.todo.database.DataBaseConnect
import br.com.lucas.todo.database.Task
import kotlinx.coroutines.launch

class ListTaskViewModel(private val context: Context) : ViewModel() {

    val taskList = MutableLiveData<List<Task>>()

    fun isTaskListEmpty(): Boolean? {
        return taskList.value?.isEmpty()
    }

    fun delete(task: Task, toast: () -> Unit) {
        viewModelScope.launch {
            DataBaseConnect.getTaskDao(context).deleteTask(task)
            refreshScreen()
            toast()
        }
    }

    fun refreshScreen() {
        viewModelScope.launch {
            val tasks = DataBaseConnect.getTaskDao(context).getAllTasks()
            val sortedTasks = tasks.sortedWith(compareBy({ it.taskDate }, { it.taskTime }))
            taskList.postValue(
                sortedTasks
            )
        }
    }
}