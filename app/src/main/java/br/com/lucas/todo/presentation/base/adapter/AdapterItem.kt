package br.com.lucas.todo.presentation.base.adapter

import java.util.*


interface AdapterItem : Equatable {
    val uuid: UUID?
}

interface Equatable {
    override fun equals(other: Any?): Boolean
}