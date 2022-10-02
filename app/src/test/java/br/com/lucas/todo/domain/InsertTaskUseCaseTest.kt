package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.InsertTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class InsertTaskUseCaseTest {

    lateinit var taskRepository: TaskRepository
    lateinit var insertTaskUseCase: InsertTaskUseCase

    @Before
    fun setup() {
        taskRepository = mockk(relaxed = true)
        insertTaskUseCase = InsertTaskUseCase(taskRepository)
    }

    @Test
    fun `insert task`() {
        val uuid = UUID.randomUUID()
        runBlocking {
            insertTaskUseCase.execute(
                Task(
                    uuid = uuid,
                    taskTitle = "title",
                    taskHour = "2",
                    taskMinute = "30",
                    taskDescription = "description",
                    taskDate = "25/10/2022",
                    isSelected = false,
                )
            )
            coVerify(exactly = 1) {
                taskRepository.insert(
                    TaskEntity(
                        uuid = uuid,
                        taskTitle = "title",
                        taskTime = 150,
                        taskDescription = "description",
                        taskDate = 1666666800000,
                        isSelected = false,
                    )
                )
            }
        }
    }

}