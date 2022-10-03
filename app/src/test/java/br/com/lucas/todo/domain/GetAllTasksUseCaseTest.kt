package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import br.com.lucas.todo.testProviders.MockedTasksProvider
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAllTasksUseCaseTest {

    private val mockedTasksProvider = MockedTasksProvider()
    private val taskMapper = TaskMapper()
    private val taskRepository = mockk<TaskRepository>(relaxed = true)
    private val getAllTasksUseCase = GetAllTasksUseCase(taskRepository, taskMapper)

    @Test
    fun `get all tasks - sorted by date-hour-min`() {
        runBlocking {
            coEvery { taskRepository.getAll() }.returns(mockedTasksProvider.getTaskEntityList())

            Truth.assertThat(getAllTasksUseCase.execute())
                .isEqualTo(mockedTasksProvider.getTaskListSortedByDate())
        }
    }
}