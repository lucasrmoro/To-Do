package br.com.tasks.domain.useCases


import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.tasks.domain.mappers.TaskMapper
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.uikit.adapter.model.Task
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
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