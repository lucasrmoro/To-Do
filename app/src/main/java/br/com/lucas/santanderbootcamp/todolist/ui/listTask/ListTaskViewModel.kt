package br.com.lucas.santanderbootcamp.todolist.ui.listTask

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.lucas.santanderbootcamp.todolist.database.DataBaseConnect
import br.com.lucas.santanderbootcamp.todolist.database.Task
import kotlinx.coroutines.launch

class ListTaskViewModel(private val context: Application) : AndroidViewModel(context) {

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
            taskList.postValue(
                DataBaseConnect.getTaskDao(context).getAllTasks()
            )
        }
    }

    fun findTaskByPosition(position: Int) = taskList.value?.get(position)
}