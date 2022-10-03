package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.useCases.DeleteSelectedTasksUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DeleteSelectedTasksUseCaseTest {

    private val taskRepository = mockk<TaskRepository>(relaxed = true)
    private val deleteSelectedTasksUseCase = DeleteSelectedTasksUseCase(taskRepository)

    @Test
    fun `delete selected tasks`() {
        runBlocking {
            deleteSelectedTasksUseCase.execute()
            coVerify(exactly = 1) { taskRepository.deleteSelected() }
        }
    }
}