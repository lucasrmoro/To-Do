package br.com.lucas.todo.presentation.base.adapter


interface AdapterItem : Equatable {
    var id: Int
}

interface Equatable {
    override fun equals(other: Any?): Boolean
}