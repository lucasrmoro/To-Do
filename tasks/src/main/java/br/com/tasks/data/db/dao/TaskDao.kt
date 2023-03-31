package br.com.tasks.data.db.dao

import androidx.room.*
import br.com.tasks.data.db.entities.TaskEntity
import java.util.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: TaskEntity)

    @Query("SELECT * FROM task_table")
    suspend fun getAll(): List<TaskEntity>

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("DELETE FROM task_table WHERE isSelected = 1")
    suspend fun deleteSelected()

}