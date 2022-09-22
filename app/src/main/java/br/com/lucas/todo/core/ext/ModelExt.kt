package br.com.lucas.todo.core.ext

import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.domain.model.Task

fun TaskEntity.toTask() = Task(
    id = uid,
    taskTitle = taskTitle,
    taskDescription = taskDescription,
    taskDate = taskDate,
    taskTime = taskTime
)

fun Task.toTaskEntity() = TaskEntity(
    uid = id,
    taskTitle = taskTitle,
    taskDescription = taskDescription,
    taskDate = taskDate,
    taskTime = taskTime
)