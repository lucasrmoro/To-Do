package br.com.lucas.todo.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "task_table")
@Parcelize
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo
    var taskTitle: String,
    @ColumnInfo
    var taskDescription: String,
    @ColumnInfo
    var taskDate: Long,
    @ColumnInfo
    var taskTime: Int
) : Parcelable