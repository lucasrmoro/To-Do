package br.com.lucas.todo.domain.model

import android.os.Parcelable
import br.com.lucas.todo.presentation.base.adapter.DiffUtilEquality
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Task(
    val uuid: UUID? = null,
    val title: String,
    val description: String,
    val date: String,
    val hour: String,
    val minute: String,
    val isSelected: Boolean = false
) : Parcelable, DiffUtilEquality<Task> {

    override fun areItemsTheSame(toCompare: Task): Boolean =
        this.uuid == toCompare.uuid

    override fun areContentsTheSame(toCompare: Task): Boolean =
        this == toCompare

}