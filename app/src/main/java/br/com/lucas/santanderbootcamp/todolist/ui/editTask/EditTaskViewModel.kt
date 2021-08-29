package br.com.lucas.santanderbootcamp.todolist.ui.editTask

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.database.DataBaseConnect
import br.com.lucas.santanderbootcamp.todolist.database.Task
import com.google.android.material.textfield.TextInputLayout
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

    fun checkTaskHourIsValid(content: String) {
        isTaskHourValid.value = content.isNotEmpty()
    }

    fun setTextInputLayoutHintColor(
        textInputLayout: TextInputLayout, context: Context, @ColorRes colorIdRes: Int) {
        textInputLayout.defaultHintTextColor =
            ColorStateList.valueOf(ContextCompat.getColor(context, colorIdRes))
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
            task!!.taskDescription = taskDescription
            checkTaskTitleIsValid(task!!.taskTitle)
            checkTaskDateIsValid(task!!.taskDate)
            checkTaskHourIsValid(task!!.taskHour)
            saveSameTask(context, task!!, closeScreen)
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
        if (isTaskTitleValid.value == true &&
            isTaskDateValid.value == true &&
            isTaskHourValid.value == true
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