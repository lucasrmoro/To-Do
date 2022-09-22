package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.data.db.entities.Task
import br.com.lucas.todo.data.db.repository.TaskRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun execute(): List<Task> = taskRepository.getAll()

}