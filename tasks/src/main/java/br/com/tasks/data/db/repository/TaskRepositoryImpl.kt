package br.com.tasks.data.db.repository

import br.com.tasks.data.db.dao.TaskDao
import br.com.tasks.data.db.entities.TaskEntity

class TaskRepositoryImpl constructor(
    private val taskDao: TaskDao
): TaskRepository {

    override suspend fun insert(task: TaskEntity) { taskDao.insert(task) }

    override suspend fun getAll(): List<TaskEntity> = taskDao.getAll()

    override suspend fun update(task: TaskEntity) { taskDao.update(task) }

    override suspend fun delete(task: TaskEntity) { taskDao.delete(task) }

    override suspend fun deleteSelected() { taskDao.deleteSelected() }

}