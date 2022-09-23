package br.com.lucas.todo.presentation.listTask

import br.com.lucas.todo.domain.model.Task

interface ListTaskAdapterInterface {

    fun onEditOptionClicked(task: Task)
    fun onDeleteOptionClicked(task: Task)
    fun onTaskClicked(task: Task)

}