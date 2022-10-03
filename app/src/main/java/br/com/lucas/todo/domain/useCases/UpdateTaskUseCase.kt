package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {

    suspend fun execute(task: Task) {
        taskRepository.update(taskMapper.mapToEntity(task))
    }

}