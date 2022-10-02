package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.core.ext.toTask
import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun execute(): List<Task> =
        taskRepository.getAll().run {
            sortedWith(
                compareBy(
                    TaskEntity::taskDate,
                    TaskEntity::taskTime
                ).thenByDescending(TaskEntity::taskTitle)
            )
        }.map { it.toTask() }

}