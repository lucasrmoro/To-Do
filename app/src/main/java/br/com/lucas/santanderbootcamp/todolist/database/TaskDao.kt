package br.com.lucas.santanderbootcamp.todolist.database

import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)
    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks() : List<Task>? = null
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
}