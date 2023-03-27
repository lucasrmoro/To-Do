package br.com.tasks.domain.useCases

import br.com.tasks.domain.mappers.TaskMapper
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.uikit.adapter.model.Task
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {

    suspend fun execute(task: Task) {
        taskRepository.delete(taskMapper.mapToEntity(task))
    }

}