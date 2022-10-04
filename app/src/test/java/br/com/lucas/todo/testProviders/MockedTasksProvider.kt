package br.com.lucas.todo.testProviders

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.domain.model.Task
import java.util.*

class MockedTasksProvider {

    private val uuidTaskEntity1 = UUID.randomUUID()
    private val uuidTaskEntity2 = UUID.randomUUID()
    private val uuidTaskEntity3 = UUID.randomUUID()

    val taskEntity1 = TaskEntity(
        uuid = uuidTaskEntity1,
        title = "titleTask1",
        description = "descriptionTask1",
        date = 1666666800000,
        time = 850,
        isSelected = false
    )
    val taskEntity2 = TaskEntity(
        uuid = uuidTaskEntity2,
        title = "titleTask2",
        description = "descriptionTask2",
        date = 1666321200000,
        time = 150,
        isSelected = false
    )
    val taskEntity3 = TaskEntity(
        uuid = uuidTaskEntity3,
        title = "titleTask3",
        description = "descriptionTask3",
        date = 1666666800000,
        time = 550,
        isSelected = false
    )
    val task1 = Task(
        uuid = uuidTaskEntity1,
        title = "titleTask1",
        hour = "14",
        minute = "10",
        description = "descriptionTask1",
        date = "25/10/2022",
        isSelected = false,
    )
    val task2 = Task(
        uuid = uuidTaskEntity2,
        title = "titleTask2",
        hour = "02",
        minute = "30",
        description = "descriptionTask2",
        date = "21/10/2022",
        isSelected = false,
    )
    val task3 = Task(
        uuid = uuidTaskEntity3,
        title = "titleTask3",
        hour = "09",
        minute = "10",
        description = "descriptionTask3",
        date = "25/10/2022",
        isSelected = false,
    )

    fun getTaskListSortedByDate() = listOf(
        task2,
        task3,
        task1
    )

    fun getTaskEntityList() = listOf(
        taskEntity3,
        taskEntity1,
        taskEntity2
    )

    fun getTaskListSortedByDateWithFewSelected() = listOf(
        task2.copy(isSelected = true),
        task3.copy(isSelected = false),
        task1.copy(isSelected = true)
    )

    fun getTaskEntityListWithFewSelected() = listOf(
        taskEntity3.copy(isSelected = false),
        taskEntity1.copy(isSelected = true),
        taskEntity2.copy(isSelected = true)
    )
}