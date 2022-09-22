package br.com.lucas.todo.data.db.repository

import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.data.db.entities.Task
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {

    override suspend fun insert(task: Task) { taskDao.insert(task) }

    override suspend fun getAll(): List<Task> = taskDao.getAll()

    override suspend fun update(task: Task) { taskDao.update(task) }

    override suspend fun delete(task: Task) { taskDao.delete(task) }

}