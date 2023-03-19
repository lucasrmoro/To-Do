package br.com.lucas.todo.domain.model.adapter

interface AdapterItem : DiffUtilEquality {

    fun itemViewType(): Int

}