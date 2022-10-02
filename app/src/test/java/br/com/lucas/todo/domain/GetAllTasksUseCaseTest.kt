package br.com.lucas.todo.domain

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class GetAllTasksUseCaseTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var getAllTasksUseCase: GetAllTasksUseCase

    private val uuidTaskEntity1 = UUID.randomUUID()
    private val uuidTaskEntity2 = UUID.randomUUID()
    private val uuidTaskEntity3 = UUID.randomUUID()

    private val taskEntity1 = TaskEntity(
        uuid = uuidTaskEntity1,
        taskTitle = "titleTask1",
        taskDescription = "descriptionTask1",
        taskDate = 1666666800000,
        taskTime = 850,
        isSelected = false
    )
    private val taskEntity2 = TaskEntity(
        uuid = uuidTaskEntity2,
        taskTitle = "titleTask2",
        taskDescription = "descriptionTask2",
        taskDate = 1666321200000,
        taskTime = 150,
        isSelected = false
    )
    private val taskEntity3 = TaskEntity(
        uuid = uuidTaskEntity3,
        taskTitle = "titleTask3",
        taskDescription = "descriptionTask3",
        taskDate = 1666666800000,
        taskTime = 550,
        isSelected = false
    )
    private val task1 = Task(
        uuid = uuidTaskEntity1,
        taskTitle = "titleTask1",
        taskHour = "14",
        taskMinute = "10",
        taskDescription = "descriptionTask1",
        taskDate = "25/10/2022",
        isSelected = false,
    )
    private val task2 = Task(
        uuid = uuidTaskEntity2,
        taskTitle = "titleTask2",
        taskHour = "02",
        taskMinute = "30",
        taskDescription = "descriptionTask2",
        taskDate = "21/10/2022",
        isSelected = false,
    )
    private val task3 = Task(
        uuid = uuidTaskEntity3,
        taskTitle = "titleTask3",
        taskHour = "09",
        taskMinute = "10",
        taskDescription = "descriptionTask3",
        taskDate = "25/10/2022",
        isSelected = false,
    )

    private val taskListSortedByDateHourAndMinute = listOf(
        task2,
        task3,
        task1
    )

    private val taskEntityList = listOf(
        taskEntity3,
        taskEntity1,
        taskEntity2
    )

    @Before
    fun setup() {
        taskRepository = mockk(relaxed = true)
        getAllTasksUseCase = GetAllTasksUseCase(taskRepository)
    }

    @Test
    fun `get all tasks - sorted by date-hour-min`() {
        runBlocking {
            coEvery { taskRepository.getAll() }.returns(taskEntityList)
            Truth.assertThat(getAllTasksUseCase.execute()).isEqualTo(taskListSortedByDateHourAndMinute)
        }
    }
}