package br.com.lucas.todo.presentation.listTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.todo.core.ext.runOnViewModelScope
import br.com.lucas.todo.data.db.entities.Task
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.domain.useCases.DeleteTaskUseCase
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase
) : ViewModel() {

    val taskList = MutableLiveData<List<Task>>()

    fun isTaskListEmpty(): Boolean? {
        return taskList.value?.isEmpty()
    }

    fun delete(task: Task, toast: () -> Unit) {
        runOnViewModelScope {
            deleteTaskUseCase.execute(task)
            refreshScreen()
            toast()
        }
    }

    fun refreshScreen() {
        runOnViewModelScope {
            taskList.postValue(getAllTasksUseCase.execute())
        }
    }
}