package br.com.tasks.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tasks.R
import br.com.tasks.domain.useCases.DeleteSelectedTasksUseCase
import br.com.tasks.domain.useCases.GetAllTasksUseCase
import br.com.tasks.domain.useCases.GetTasksCategorizedByDateUseCase
import br.com.tasks.domain.useCases.UpdateTaskUseCase
import br.com.uikit.adapter.model.AdapterItem
import br.com.uikit.adapter.model.Task
import br.com.uikit.extensions.viewModelCall
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTasksCategorizedByDateUseCase: GetTasksCategorizedByDateUseCase,
    private val deleteSelectedTasksUseCase: DeleteSelectedTasksUseCase
) : ViewModel() {

    private var _taskList = MutableLiveData<List<AdapterItem>>()
    val taskList: LiveData<List<AdapterItem>>
        get() = _taskList

    private var _hasSelectedTasks = MutableLiveData<Boolean>()
    val hasSelectedTasks: LiveData<Boolean>
        get() = _hasSelectedTasks

    var selectedTasksQuantity: Int = 0
        private set

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
                _taskList.postValue(getTasksCategorizedByDateUseCase.execute(dbTaskList))
                _hasSelectedTasks.value = dbTaskList.any { it.isSelected } == true
                selectedTasksQuantity = dbTaskList.filter { it.isSelected }.size
            },
            onError = { onError(R.string.task_failure_on_get_all) },
        )
    }
}