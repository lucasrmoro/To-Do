package br.com.lucas.todo.data.db.repository

import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.data.db.entities.TaskEntity
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {

    override suspend fun insert(task: TaskEntity) { taskDao.insert(task) }

    override suspend fun getAll(): List<TaskEntity> = taskDao.getAll()

    override suspend fun update(task: TaskEntity) { taskDao.update(task) }

    override suspend fun delete(task: TaskEntity) { taskDao.delete(task) }

}