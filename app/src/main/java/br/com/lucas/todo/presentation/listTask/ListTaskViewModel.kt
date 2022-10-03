package br.com.lucas.todo.presentation.listTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucas.todo.R
import br.com.lucas.todo.core.ext.viewModelCall
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.DeleteSelectedTasksUseCase
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteSelectedTasksUseCase: DeleteSelectedTasksUseCase
) : ViewModel() {

    private var _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>>
        get() = _taskList

    private var _hasSelectedTasks = MutableLiveData<Boolean>()
    val hasSelectedTasks: LiveData<Boolean>
        get() = _hasSelectedTasks

    val selectedTasksQuantity: Int
        get() = _taskList.value?.filter { it.isSelected }?.size ?: 0

    fun isTaskListEmpty() = taskList.value?.isEmpty() != false

    fun syncSelection(isSelected: Boolean, task: Task) {
        viewModelCall(
            callToDo = { updateTaskUseCase.execute(task.copy(isSelected = isSelected)) },
            onFinishCall = { refreshScreen() }
        )
    }

    fun deleteSelectedTasks(toast: (Int) -> Unit = { }) {
        viewModelCall(
            callToDo = { deleteSelectedTasksUseCase.execute() },
            onSuccess = { toast(R.string.task_successfully_deleted) },
            onError = { toast(R.string.tasks_failure_on_delete) },
            onFinishCall = { refreshScreen(toast) }
        )
    }

    fun refreshScreen(onError: (Int) -> Unit = { }) {
        viewModelCall(
            callToDo = { getAllTasksUseCase.execute() },
            onSuccess = { dbTaskList ->
                _taskList.postValue(dbTaskList)
                _hasSelectedTasks.value = dbTaskList.any { it.isSelected } == true
            },
            onError = { onError(R.string.task_failure_on_get_all) },
        )
    }
}