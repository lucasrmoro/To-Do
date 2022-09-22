package br.com.lucas.todo.presentation.listTask

import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.base.adapter.BaseAdapterInterface

interface ListTaskAdapterInterface : BaseAdapterInterface {

    fun onEditOptionClicked(task: Task)
    fun onDeleteOptionClicked(task: Task)
    fun onTaskClicked(task: Task)

}