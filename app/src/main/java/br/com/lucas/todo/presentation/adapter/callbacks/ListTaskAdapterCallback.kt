package br.com.lucas.todo.presentation.adapter.callbacks

import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.adapter.callbacks.generic.GenericAdapterCallback

interface ListTaskAdapterCallback: GenericAdapterCallback {

    fun onTaskClicked(task: Task)
    fun syncSelection(isSelected: Boolean, task: Task)

}