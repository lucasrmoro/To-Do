package br.com.lucas.todo.data.db.dao

import androidx.room.*
import br.com.lucas.todo.data.db.entities.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}