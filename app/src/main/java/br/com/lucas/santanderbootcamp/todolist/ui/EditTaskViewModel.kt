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
    val isTaskDescriptionValid = MutableLiveData<Boolean>()
    val isTaskDateValid = MutableLiveData<Boolean>()
    val isTaskHourValid = MutableLiveData<Boolean>()

    var task: Task? = null
        private set

    fun checkTaskTitleIsValid(content: String) {
        isTaskTitleValid.value = content.length >= 3
    }

    fun checkTaskDescriptionIsValid(content: String) {
        isTaskDescriptionValid.value = content.length >= 5
    }

    fun checkTaskDateIsValid(content: String){
        isTaskDateValid.value = content.isNotEmpty()
    }

    fun checkTaskHourIsValid(content: String){
        isTaskHourValid.value = content.isNotEmpty()
    }

    fun onSaveEvent(context: Context, task: Task, closeScreen: (() -> Unit)) {
        saveNewTask(context, task, closeScreen)
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
        task: Task,
        closeScreen: () -> Unit
    ) {
        if (isTaskTitleValid.value == true && isTaskDescriptionValid.value == true &&
                isTaskDateValid.value == true && isTaskHourValid.value == true) {
            viewModelScope.launch {
                DataBaseConnect.getTaskDao(context).insertTask(
                    Task(
                        taskTitle= task.taskTitle,
                        taskDescription = task.taskDescription,
                        taskDate = task.taskDate,
                        taskHour = task.taskHour,
                        uid = 0
                    )
                )
                Toast.makeText(context, "Successfully saved!", Toast.LENGTH_SHORT).show()
                closeScreen()
            }
        } else {
            Toast.makeText(context, "Fill all required fields!", Toast.LENGTH_SHORT).show()
        }
    }
}