package br.com.lucas.todo.data.db.repository

import br.com.lucas.todo.data.db.entities.Task

interface TaskRepository {

    suspend fun insert(task: Task)

    suspend fun getAll(): List<Task>

    suspend fun update(task: Task)

    suspend fun delete(task: Task)

}