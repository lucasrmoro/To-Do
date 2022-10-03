package br.com.lucas.todo.domain.useCases


import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {

    suspend fun execute(): List<Task> {
        val tasksSorted = taskRepository.getAll().run {
            sortedWith(
                compareBy(
                    TaskEntity::taskDate,
                    TaskEntity::taskTime
                ).thenByDescending(TaskEntity::taskTitle)
            )
        }
        return taskMapper.mapToDomainModels(tasksSorted)
    }


}