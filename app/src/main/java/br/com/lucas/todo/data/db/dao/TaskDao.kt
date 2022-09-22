package br.com.lucas.todo.data.db.dao

import androidx.room.*
import br.com.lucas.todo.data.db.entities.TaskEntity

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
}