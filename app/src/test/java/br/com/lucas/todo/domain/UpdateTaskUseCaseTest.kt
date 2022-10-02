package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class UpdateTaskUseCaseTest {

    lateinit var taskRepository: TaskRepository
    lateinit var updateTaskUseCase: UpdateTaskUseCase

    @Before
    fun setup() {
        taskRepository = mockk(relaxed = true)
        updateTaskUseCase = UpdateTaskUseCase(taskRepository)
    }

    @Test
    fun `update task`() {
        val uuid = UUID.randomUUID()
        runBlocking {
            updateTaskUseCase.execute(
                Task(
                    uuid = uuid,
                    taskTitle = "updateTitle",
                    taskHour = "2",
                    taskMinute = "30",
                    taskDescription = "updateDescription",
                    taskDate = "25/10/2022",
                    isSelected = false,
                )
            )

            coVerify(exactly = 1) {
                taskRepository.update(
                    TaskEntity(
                        uuid = uuid,
                        taskTitle = "updateTitle",
                        taskTime = 150,
                        taskDescription = "updateDescription",
                        taskDate = 1666666800000,
                        isSelected = false,
                    )
                )
            }
        }
    }
}