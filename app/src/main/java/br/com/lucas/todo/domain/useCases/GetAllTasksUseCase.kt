package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun execute(): List<TaskEntity> =
        taskRepository.getAll().sortedWith(compareBy({ it.taskDate }, { it.taskTime }))

}