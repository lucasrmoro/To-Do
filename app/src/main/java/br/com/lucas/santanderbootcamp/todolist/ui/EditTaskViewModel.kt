package br.com.lucas.santanderbootcamp.todolist.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.santanderbootcamp.todolist.database.DataBaseConnect
import br.com.lucas.santanderbootcamp.todolist.database.Task
import kotlinx.coroutines.launch

class EditTaskViewModel : ViewModel() {

    val isTaskTitleValid = MutableLiveData<Boolean>()
    val isTaskDateValid = MutableLiveData<Boolean>()
    val isTaskHourValid = MutableLiveData<Boolean>()

    var task: Task? = null
        private set

    fun setup(task: Task) {
        this.task = task
    }

    fun checkTaskTitleIsValid(content: String) {
        isTaskTitleValid.value = content.length >= 3
    }

    fun checkTaskDateIsValid(content: String) {
        isTaskDateValid.value = content.isNotEmpty()
    }

    fun checkTaskHourIsValid(content: String){
        isTaskHourValid.value = content.isNotEmpty()
    }

    fun onSaveEvent(context: Context, taskTitle: String, taskDescription: String,
                    taskDate: String, taskHour: String,
                    closeScreen: (() -> Unit)) {
        saveNewTask(context, taskTitle, taskDescription, taskDate, taskHour,  closeScreen)
    }

    fun delete(context: Context, closeScreen: () -> Unit) {
        val task = task ?: return
        viewModelScope.launch {
            DataBaseConnect.getTaskDao(context).deleteTask(task)
            closeScreen()
        }
    }

    private fun saveNewTask(
        context: Context,
        taskTitle: String, taskDescription: String, taskDate: String, taskHour: String,
        closeScreen: () -> Unit
    ) {
        if (isTaskTitleValid.value == true &&
            isTaskDateValid.value == true &&
            isTaskHourValid.value == true
        ) {
            viewModelScope.launch {
                DataBaseConnect.getTaskDao(context).insertTask(
                    Task(
                        taskTitle = taskTitle,
                        taskDescription = taskDescription,
                        taskDate = taskDate,
                        taskHour = taskHour,
                        uid = 0
                    )
                )
                Toast.makeText(context, "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show()
                closeScreen()
            }
        } else {
            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }
}