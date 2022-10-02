package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.DeleteTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class DeleteTaskUseCaseTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    private val uuidTaskEntity = UUID.randomUUID()
    private val taskEntity = TaskEntity(
        uuid = uuidTaskEntity,
        taskTitle = "titleTask",
        taskDescription = "descriptionTask",
        taskDate = 1666666800000,
        taskTime = 850,
        isSelected = false
    )
    private val task = Task(
        uuid = uuidTaskEntity,
        taskTitle = "titleTask",
        taskHour = "14",
        taskMinute = "10",
        taskDescription = "descriptionTask",
        taskDate = "25/10/2022",
        isSelected = false,
    )

    @Before
    fun setup() {
        taskRepository = mockk(relaxed = true)
        deleteTaskUseCase = DeleteTaskUseCase(taskRepository)
    }

    @Test
    fun `delete task`() {
        runBlocking {
            deleteTaskUseCase.execute(task)
            coVerify(exactly = 1) { taskRepository.delete(taskEntity) }
        }
    }
}