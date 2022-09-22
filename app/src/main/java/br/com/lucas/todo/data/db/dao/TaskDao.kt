package br.com.lucas.todo.data.db.dao

import androidx.room.*
import br.com.lucas.todo.data.db.entities.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM task_table")
    suspend fun getAll(): List<Task>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}