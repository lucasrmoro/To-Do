package br.com.tasks.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey
    val uuid: UUID,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var date: Long,
    @ColumnInfo
    var time: Int,
    @ColumnInfo
    var isSelected: Boolean
)