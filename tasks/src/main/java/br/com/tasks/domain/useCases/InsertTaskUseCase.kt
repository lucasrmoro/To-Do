package br.com.tasks.domain.useCases

import br.com.tasks.domain.mappers.TaskMapper
import br.com.tasks.data.db.repository.TaskRepository
import br.com.uikit.adapter.model.Task

class InsertTaskUseCase constructor(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {

    suspend fun execute(task: Task) { taskRepository.insert(taskMapper.mapToEntity(task)) }

}