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
        taskTitle = "titleTask1",
        taskDescription = "descriptionTask1",
        taskDate = 1666666800000,
        taskTime = 850,
        isSelected = false
    )
    val taskEntity2 = TaskEntity(
        uuid = uuidTaskEntity2,
        taskTitle = "titleTask2",
        taskDescription = "descriptionTask2",
        taskDate = 1666321200000,
        taskTime = 150,
        isSelected = false
    )
    val taskEntity3 = TaskEntity(
        uuid = uuidTaskEntity3,
        taskTitle = "titleTask3",
        taskDescription = "descriptionTask3",
        taskDate = 1666666800000,
        taskTime = 550,
        isSelected = false
    )
    val task1 = Task(
        uuid = uuidTaskEntity1,
        taskTitle = "titleTask1",
        taskHour = "14",
        taskMinute = "10",
        taskDescription = "descriptionTask1",
        taskDate = "25/10/2022",
        isSelected = false,
    )
    val task2 = Task(
        uuid = uuidTaskEntity2,
        taskTitle = "titleTask2",
        taskHour = "02",
        taskMinute = "30",
        taskDescription = "descriptionTask2",
        taskDate = "21/10/2022",
        isSelected = false,
    )
    val task3 = Task(
        uuid = uuidTaskEntity3,
        taskTitle = "titleTask3",
        taskHour = "09",
        taskMinute = "10",
        taskDescription = "descriptionTask3",
        taskDate = "25/10/2022",
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