package br.com.tasks.data.db.repository

import br.com.tasks.data.db.entities.TaskEntity

interface TaskRepository {

    suspend fun insert(task: TaskEntity)

    suspend fun getAll(): List<TaskEntity>

    suspend fun update(task: TaskEntity)

    suspend fun delete(task: TaskEntity)

    suspend fun deleteSelected()
}