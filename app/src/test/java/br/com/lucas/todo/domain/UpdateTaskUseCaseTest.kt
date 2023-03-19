package br.com.lucas.todo.domain

import br.com.lucas.todo.core.providers.date.DateProvider
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import br.com.lucas.todo.testProviders.MockedTasksProvider
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UpdateTaskUseCaseTest {

    private val mockedTasksProvider = MockedTasksProvider()
    private val dateProvider = mockk<DateProvider>()
    private val taskMapper = TaskMapper(dateProvider)
    private val taskRepository = mockk<TaskRepository>(relaxed = true)
    private val updateTaskUseCase = UpdateTaskUseCase(taskRepository, taskMapper)

    @Test
    fun `update task`() {
        runBlocking {
            updateTaskUseCase.execute(mockedTasksProvider.task1)

            coVerify(exactly = 1) {
                taskRepository.update(mockedTasksProvider.taskEntity1)
            }
        }
    }
}