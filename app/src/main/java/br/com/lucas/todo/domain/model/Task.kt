package br.com.lucas.todo.domain.model

import android.os.Parcelable
import br.com.lucas.todo.presentation.base.adapter.DiffUtilEquality
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Task(
    var uuid: UUID,
    var taskTitle: String,
    var taskDescription: String,
    var taskDate: String,
    var taskTime: String
) : Parcelable, DiffUtilEquality<Task> {

    override fun areItemsTheSame(toCompare: Task): Boolean =
        this.uuid == toCompare.uuid

    override fun areContentsTheSame(toCompare: Task): Boolean =
        this == toCompare

}