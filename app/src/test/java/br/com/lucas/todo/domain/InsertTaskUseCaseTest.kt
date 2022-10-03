package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.domain.useCases.InsertTaskUseCase
import br.com.lucas.todo.testProviders.MockedTasksProvider
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InsertTaskUseCaseTest {

    private val mockedTasksProvider = MockedTasksProvider()
    private val taskMapper = TaskMapper()
    private val taskRepository = mockk<TaskRepository>(relaxed = true)
    private val insertTaskUseCase = InsertTaskUseCase(taskRepository, taskMapper)

    @Test
    fun `insert task`() {
        runBlocking {
            insertTaskUseCase.execute(mockedTasksProvider.task1)

            coVerify(exactly = 1) {
                taskRepository.insert(mockedTasksProvider.taskEntity1)
            }
        }
    }

}