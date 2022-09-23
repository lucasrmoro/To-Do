package br.com.lucas.todo.core.ext

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.domain.model.Task
import java.util.*

fun TaskEntity.toTask() = Task(
    uuid = uuid,
    taskTitle = taskTitle,
    taskDescription = taskDescription,
    taskDate = taskDate.formatLongToStringDate(),
    taskTime = taskTime.formatIntTimeToHoursAndMinutes()
)

fun Task.toTaskEntity() = TaskEntity(
    uuid = currentOrNewRandomUUID(),
    taskTitle = taskTitle,
    taskDescription = taskDescription,
    taskDate = taskDate.formatStringDateToLong(),
    taskTime = taskTime.formatHoursAndMinutesToIntTime()
)

fun Task?.currentOrNewRandomUUID(): UUID = this?.uuid ?: UUID.randomUUID()
