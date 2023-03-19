package br.com.lucas.todo.domain

import br.com.lucas.todo.core.providers.date.DateProvider
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.domain.useCases.DeleteTaskUseCase
import br.com.lucas.todo.testProviders.MockedTasksProvider
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteTaskUseCaseTest {

    private val mockedTasksProvider = MockedTasksProvider()
    private val dateProvider = mockk<DateProvider>()
    private val taskMapper = TaskMapper(dateProvider)
    private val taskRepository = mockk<TaskRepository>(relaxed = true)
    private val deleteTaskUseCase = DeleteTaskUseCase(taskRepository, taskMapper)

    @Test
    fun `delete task`() {
        runTest {
            deleteTaskUseCase.execute(mockedTasksProvider.task1)

            coVerify(exactly = 1) { taskRepository.delete(mockedTasksProvider.taskEntity1) }
        }
    }
}