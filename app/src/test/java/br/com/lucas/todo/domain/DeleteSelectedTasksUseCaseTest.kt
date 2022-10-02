package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.useCases.DeleteSelectedTasksUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteSelectedTasksUseCaseTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var deleteSelectedTasksUseCase: DeleteSelectedTasksUseCase

    @Before
    fun setup() {
        taskRepository = mockk(relaxed = true)
        deleteSelectedTasksUseCase = DeleteSelectedTasksUseCase(taskRepository)
    }

    @Test
    fun `delete selected tasks`() {
        runBlocking {
            deleteSelectedTasksUseCase.execute()
            coVerify(exactly = 1) { taskRepository.deleteSelected() }
        }
    }
}