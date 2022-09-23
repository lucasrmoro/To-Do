package br.com.lucas.todo.presentation.base.adapter

interface DiffUtilEquality<T> {

    fun areItemsTheSame(toCompare: T): Boolean
    fun areContentsTheSame(toCompare: T): Boolean

}