package br.com.tasks.domain.useCases

import br.com.tasks.data.db.repository.TaskRepository

class DeleteSelectedTasksUseCase constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun execute() { taskRepository.deleteSelected() }

}