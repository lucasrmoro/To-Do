package br.com.tasks.domain.useCases


import br.com.tasks.data.db.entities.TaskEntity
import br.com.tasks.domain.mappers.TaskMapper
import br.com.tasks.data.db.repository.TaskRepository
import br.com.uikit.adapter.model.Task

class GetAllTasksUseCase constructor(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {

    suspend fun execute(): List<Task> {
        val tasksSorted = taskRepository.getAll().run {
            sortedWith(
                compareBy(
                    TaskEntity::date,
                    TaskEntity::time
                ).thenByDescending(TaskEntity::title)
            )
        }
        return taskMapper.mapToDomainModels(tasksSorted)
    }


}