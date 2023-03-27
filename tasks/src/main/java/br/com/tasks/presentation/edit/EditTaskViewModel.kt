package br.com.tasks.presentation.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tasks.R
import br.com.tasks.domain.useCases.InsertTaskUseCase
import br.com.tasks.domain.useCases.UpdateTaskUseCase
import br.com.uikit.adapter.model.Task
import br.com.uikit.extensions.isTrue
import br.com.uikit.extensions.viewModelCall
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
        Task(title = "", description = "", date = "", minute = "", hour = "")
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
            title = taskTitle,
            description = taskDescription,
            date = taskDate,
            minute = taskMinute,
            hour = taskHour
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
            checkTaskTitleIsValid(title)
            checkTaskDateIsValid(date)
            checkTaskTimeIsValid(hour)

            return isTaskTitleValid.isTrue() && isTaskDateValid.isTrue() && isTaskTimeValid.isTrue()
        }
    }
}