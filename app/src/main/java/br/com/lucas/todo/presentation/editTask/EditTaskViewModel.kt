package br.com.lucas.todo.presentation.editTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucas.todo.R
import br.com.lucas.todo.core.ext.isTrue
import br.com.lucas.todo.core.ext.viewModelCall
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.InsertTaskUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    val isTaskTitleValid = MutableLiveData<Boolean>()
    val isTaskDateValid = MutableLiveData<Boolean>()
    val isTaskTimeValid = MutableLiveData<Boolean>()

    private var task: Task =
        Task(taskTitle = "", taskDescription = "", taskDate = "", taskMinute = "", taskHour = "")
    private var isEditMode = false

    fun setEditModeEnabled(task: Task) {
        this.task = task
        isEditMode = true
    }

    fun checkTaskTitleIsValid(content: String) {
        isTaskTitleValid.value = content.length >= 3
    }

    fun checkTaskDateIsValid(content: String) {
        isTaskDateValid.value = content.isNotEmpty()
    }

    fun checkTaskTimeIsValid(content: String) {
        isTaskTimeValid.value = content.isNotEmpty()
    }

    fun onSaveEvent(
        taskTitle: String,
        taskDescription: String,
        taskDate: String,
        taskHour: String,
        taskMinute: String,
        toast: (Int) -> Unit = {},
        onFieldsNotValid: () -> Unit = {}
    ) {
        val taskToSave = task.copy(
            taskTitle = taskTitle,
            taskDescription = taskDescription,
            taskDate = taskDate,
            taskMinute = taskMinute,
            taskHour = taskHour
        )

        if (areFieldsValid(taskToSave)) {
            viewModelCall(
                callToDo = {
                    if (isEditMode) {
                        updateTaskUseCase.execute(taskToSave)
                    } else {
                        insertTaskUseCase.execute(taskToSave)
                    }
                },
                onSuccess = {
                    toast(if (isEditMode) R.string.task_successfully_edited else R.string.task_successfully_created)
                },
                onError = {
                    toast(if (isEditMode) R.string.task_failure_on_update else R.string.task_failure_on_create)
                }
            )
        } else {
            onFieldsNotValid()
        }
    }

    private fun areFieldsValid(task: Task): Boolean {
        with(task) {
            checkTaskTitleIsValid(taskTitle)
            checkTaskDateIsValid(taskDate)
            checkTaskTimeIsValid(taskHour)

            return isTaskTitleValid.isTrue() && isTaskDateValid.isTrue() && isTaskTimeValid.isTrue()
        }
    }
}