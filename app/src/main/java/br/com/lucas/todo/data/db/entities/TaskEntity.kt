package br.com.lucas.todo.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
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
)