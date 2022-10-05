package br.com.lucas.todo.presentation.common.generic.adapter.model


interface DiffUtilEquality {

    fun areItemsTheSame(toCompare: Any): Boolean
    fun areContentsTheSame(toCompare: Any): Boolean

}