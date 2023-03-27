package br.com.tasks.domain.useCases

import br.com.lucas.todo.data.db.repository.TaskRepository
import javax.inject.Inject

class DeleteSelectedTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun execute() { taskRepository.deleteSelected() }

}