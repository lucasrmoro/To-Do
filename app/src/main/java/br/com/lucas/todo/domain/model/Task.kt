package br.com.lucas.todo.domain.model

import android.os.Parcelable
import br.com.lucas.todo.presentation.base.adapter.AdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    override var id: Int,
    var taskTitle: String,
    var taskDescription: String,
    var taskDate: Long,
    var taskTime: Int
) : Parcelable, AdapterItem