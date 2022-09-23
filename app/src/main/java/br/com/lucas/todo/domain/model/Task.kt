package br.com.lucas.todo.domain.model

import android.os.Parcelable
import br.com.lucas.todo.presentation.base.adapter.AdapterItem
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Task(
    var taskTitle: String,
    var taskDescription: String,
    var taskDate: String,
    var taskTime: String,
    override val uuid: UUID?
) : Parcelable, AdapterItem