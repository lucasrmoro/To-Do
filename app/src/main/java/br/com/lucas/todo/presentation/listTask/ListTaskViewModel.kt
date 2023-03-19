package br.com.lucas.todo.presentation.listTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lucas.todo.R
import br.com.lucas.todo.core.base.viewModel.BaseViewModel
import br.com.lucas.todo.core.ext.safeCall
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.DeleteSelectedTasksUseCase
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import br.com.lucas.todo.domain.useCases.GetTasksCategorizedByDateUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import br.com.lucas.todo.domain.model.adapter.AdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTasksCategorizedByDateUseCase: GetTasksCategorizedByDateUseCase,
    private val deleteSelectedTasksUseCase: DeleteSelectedTasksUseCase
) : BaseViewModel() {

    private var _taskList = MutableLiveData<List<AdapterItem>>()
    val taskList: LiveData<List<AdapterItem>>
        get() = _taskList

    private var _hasSelectedTasks = MutableLiveData<Boolean>()
    val hasSelectedTasks: LiveData<Boolean>
        get() = _hasSelectedTasks

    var selectedTasksQuantity: Int = 0
        private set

    fun isTaskListEmpty() =
        taskList.value?.isEmpty() == true

    fun syncSelection(isSelected: Boolean, task: Task) {
        launch {
            safeCall(
                callToDo = { updateTaskUseCase.execute(task.copy(isSelected = isSelected)) },
                onFinishCall = { refreshScreen() }
            )
        }
    }

    fun deleteSelectedTasks(toast: (Int) -> Unit = { }) {
        launch {
            safeCall(
                callToDo = { deleteSelectedTasksUseCase.execute() },
                onSuccess = { toast(R.string.task_successfully_deleted) },
                onError = { toast(R.string.tasks_failure_on_delete) },
                onFinishCall = { refreshScreen(toast) }
            )
        }
    }

    fun refreshScreen(onError: (Int) -> Unit = { }) {
        launch {
            safeCall(
                callToDo = { getAllTasksUseCase.execute() },
                onSuccess = { dbTaskList ->
                    _taskList.postValue(getTasksCategorizedByDateUseCase.execute(dbTaskList))
                    _hasSelectedTasks.value = dbTaskList.any { it.isSelected } == true
                    selectedTasksQuantity = dbTaskList.filter { it.isSelected }.size
                },
                onError = { onError(R.string.task_failure_on_get_all) }
            )
        }
    }
}