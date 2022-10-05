package br.com.lucas.todo.presentation.common.generic.adapter.model

interface AdapterItem : DiffUtilEquality {

    fun itemViewType(): Int

}