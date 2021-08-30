package br.com.lucas.santanderbootcamp.todolist.ui.editTask

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.core.extensions.convertStringToLong
import br.com.lucas.santanderbootcamp.todolist.database.DataBaseConnect
import br.com.lucas.santanderbootcamp.todolist.database.Task
import kotlinx.coroutines.launch

class EditTaskViewModel : ViewModel() {

    val isTaskTitleValid = MutableLiveData<Boolean>()
    val isTaskDateEmpty = MutableLiveData<Boolean>()
    val isTaskHourEmpty = MutableLiveData<Boolean>()

    var task: Task? = null
        private set

    fun setup(task: Task) {
        this.task = task
    }

    fun checkTaskTitleIsValid(content: String) {
        isTaskTitleValid.value = content.length >= 3
    }

    fun checkTaskDateIsEmpty(content: String) {
        isTaskDateEmpty.value = content.isNotEmpty()
    }

    fun checkTaskHourIsEmpty(content: String) {
        isTaskHourEmpty.value = content.isNotEmpty()
    }

    fun onSaveEvent(
        context: Context, taskTitle: String, taskDescription: String,
        taskDate: String, taskHour: String,
        closeScreen: (() -> Unit)
    ) {
        if (task == null) {
            saveNewTask(context, taskTitle, taskDescription, taskDate, taskHour, closeScreen)
        } else {
            task!!.taskTitle = taskTitle
            task!!.taskHour = taskHour
            task!!.taskDate = taskDate.convertStringToLong()
            task!!.taskDescription = taskDescription
            saveSameTask(context, task!!, closeScreen)
        }
    }

    private fun saveNewTask(
        context: Context,
        taskTitle: String, taskDescription: String, taskDate: String, taskHour: String,
        closeScreen: () -> Unit
    ) {
        checkTaskTitleIsValid(taskTitle)
        checkTaskDateIsEmpty(taskDate)
        checkTaskHourIsEmpty(taskHour)

        if (isTaskTitleValid.value == true &&
            isTaskDateEmpty.value == true &&
            isTaskHourEmpty.value == true
        ) {
            viewModelScope.launch {
                DataBaseConnect.getTaskDao(context).insertTask(
                    Task(
                        taskTitle = taskTitle,
                        taskDescription = taskDescription,
                        taskDate = taskDate.convertStringToLong(),
                        taskHour = taskHour,
                        uid = 0
                    )
                )
                Toast.makeText(
                    context,
                    context.getString(R.string.successfully_created),
                    Toast.LENGTH_SHORT
                ).show()
                closeScreen()
            }
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.fill_all_required_fields),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveSameTask(
        context: Context,
        task: Task,
        closeScreen: () -> Unit
    ) {
        checkTaskTitleIsValid(task.taskTitle)
        checkTaskDateIsEmpty(task.taskDate.toString())
        checkTaskHourIsEmpty(task.taskHour)

        if (isTaskTitleValid.value == true &&
            isTaskDateEmpty.value == true &&
            isTaskHourEmpty.value == true
        ) {
            viewModelScope.launch {
                DataBaseConnect.getTaskDao(context).updateTask(
                    task
                )
                Toast.makeText(
                    context,
                    context.getString(R.string.successfully_edited),
                    Toast.LENGTH_SHORT
                ).show()
                closeScreen()
            }
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.fill_all_required_fields),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}