package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.core.ext.toTaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import javax.inject.Inject

class InsertTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun execute(task: Task) { taskRepository.insert(task.toTaskEntity()) }

}