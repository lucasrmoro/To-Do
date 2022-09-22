package br.com.lucas.todo.presentation.editTask

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.todo.R
import br.com.lucas.todo.core.ext.convertStringToLong
import br.com.lucas.todo.core.ext.runOnViewModelScope
import br.com.lucas.todo.data.db.entities.Task
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.domain.useCases.InsertTaskUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
): ViewModel() {

    val isTaskTitleValid = MutableLiveData<Boolean>()
    val isTaskDateValid = MutableLiveData<Boolean>()
    val isTaskTimeValid = MutableLiveData<Boolean>()

    var task: Task? = null
        private set

    var totalTaskTime: Int? = null
        private set

    fun setup(task: Task) {
        this.task = task
        totalTaskTime = task.taskTime
    }

    fun checkTaskTitleIsValid(content: String) {
        isTaskTitleValid.value = content.length >= 3
    }

    fun checkTaskDateIsValid(content: String) {
        isTaskDateValid.value = content.isNotEmpty()
    }

    fun convertHourAndMinutesToFullTime(hour: Int, minutes: Int) {
        val hoursInMinutes = hour * 60
        totalTaskTime = hoursInMinutes + minutes
    }

    fun checkTaskTimeIsValid() {
        isTaskTimeValid.value = totalTaskTime != null
    }

    fun onSaveEvent(
        context: Context, taskTitle: String, taskDescription: String,
        taskDate: String,
        closeScreen: (() -> Unit)
    ) {
        if (task == null) {
            saveNewTask(context, taskTitle, taskDescription, taskDate, closeScreen)
        } else {
            task!!.taskTitle = taskTitle
            task!!.taskTime = totalTaskTime!!
            task!!.taskDate = taskDate.convertStringToLong()
            task!!.taskDescription = taskDescription
            saveSameTask(context, task!!, closeScreen)
        }
    }

    private fun saveNewTask(
        context: Context,
        taskTitle: String, taskDescription: String, taskDate: String,
        closeScreen: () -> Unit
    ) {
        checkTaskTitleIsValid(taskTitle)
        checkTaskDateIsValid(taskDate)
        checkTaskTimeIsValid()

        if (isTaskTitleValid.value == true &&
            isTaskDateValid.value == true &&
            isTaskTimeValid.value == true
        ) {
            runOnViewModelScope {
                insertTaskUseCase.execute(
                    Task(
                        taskTitle = taskTitle,
                        taskDescription = taskDescription,
                        taskDate = taskDate.convertStringToLong(),
                        taskTime = totalTaskTime!!,
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
            fillAllRequiredFieldsToast(context)
        }
    }

    private fun saveSameTask(
        context: Context,
        task: Task,
        closeScreen: () -> Unit
    ) {
        checkTaskTitleIsValid(task.taskTitle)
        checkTaskDateIsValid(task.taskDate.toString())
        checkTaskTimeIsValid()

        if (isTaskTitleValid.value == true &&
            isTaskDateValid.value == true &&
            isTaskTimeValid.value == true
        ) {
            runOnViewModelScope {
                updateTaskUseCase.execute(task)
                Toast.makeText(
                    context,
                    context.getString(R.string.successfully_edited),
                    Toast.LENGTH_SHORT
                ).show()
                closeScreen()
            }
        } else {
            fillAllRequiredFieldsToast(context)
        }
    }

    private fun fillAllRequiredFieldsToast(context: Context) {
        Toast.makeText(
            context,
            context.getString(R.string.fill_all_required_fields),
            Toast.LENGTH_SHORT
        ).show()
    }
}