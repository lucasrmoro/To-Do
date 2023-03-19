package br.com.lucas.todo.presentation.editTask

import androidx.lifecycle.MutableLiveData
import br.com.lucas.todo.R
import br.com.lucas.todo.core.base.viewModel.BaseViewModel
import br.com.lucas.todo.core.ext.isTrue
import br.com.lucas.todo.core.ext.safeCall
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.InsertTaskUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : BaseViewModel() {

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
        launch {
            val taskToSave = task.copy(
                title = taskTitle,
                description = taskDescription,
                date = taskDate,
                minute = taskMinute,
                hour = taskHour
            )

            if (areFieldsValid(taskToSave)) {
                safeCall(
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