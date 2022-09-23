package br.com.lucas.todo.presentation.listTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucas.todo.R
import br.com.lucas.todo.core.ext.viewModelCall
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.DeleteTaskUseCase
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase
) : ViewModel() {

    val taskList = MutableLiveData<List<Task>>()

    fun isTaskListEmpty() = taskList.value?.isEmpty() == true

    fun delete(task: Task, toast: (Int) -> Unit) {
        viewModelCall(
            callToDo = { deleteTaskUseCase.execute(task) },
            onSuccess = { toast(R.string.task_successfully_deleted) },
            onError = { toast(R.string.task_failure_on_delete) },
            onFinishCall = { refreshScreen(toast) }
        )
    }

    fun refreshScreen(onError: (Int) -> Unit) {
        viewModelCall(
            callToDo = { getAllTasksUseCase.execute() },
            onSuccess = { taskList.postValue(it) },
            onError = { onError(R.string.task_failure_on_get_all) }
        )
    }
}