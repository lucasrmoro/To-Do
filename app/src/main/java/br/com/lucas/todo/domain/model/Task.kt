package br.com.lucas.todo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val uid: Int,
    var taskTitle: String,
    var taskDescription: String,
    var taskDate: Long,
    var taskTime: Int
) : Parcelable