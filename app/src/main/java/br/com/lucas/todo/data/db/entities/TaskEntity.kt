package br.com.lucas.todo.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey
    val uuid: UUID,
    @ColumnInfo
    var taskTitle: String,
    @ColumnInfo
    var taskDescription: String,
    @ColumnInfo
    var taskDate: Long,
    @ColumnInfo
    var taskTime: Int,
    @ColumnInfo
    var isSelected: Boolean
)