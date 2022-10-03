package br.com.lucas.todo.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.DeleteTaskUseCase
import br.com.lucas.todo.rules.CoroutinesTestRule
import br.com.lucas.todo.rules.TimberRule
import br.com.lucas.todo.testProviders.MockedTasksProvider
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteTaskUseCaseTest {

    private val mockedTasksProvider = MockedTasksProvider()
    private val taskMapper = TaskMapper()
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