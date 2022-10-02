package br.com.lucas.todo.presentation.listTask

import br.com.lucas.todo.domain.model.Task

interface ListTaskAdapterInterface {

    fun onTaskClicked(task: Task)
    fun syncSelection(isSelected: Boolean, task: Task)

}