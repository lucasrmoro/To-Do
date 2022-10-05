package br.com.lucas.todo.presentation.listTask.adapter

import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.common.generic.adapter.GenericAdapterCallback

interface ListTaskAdapterCallback: GenericAdapterCallback {

    fun onTaskClicked(task: Task)
    fun syncSelection(isSelected: Boolean, task: Task)

}