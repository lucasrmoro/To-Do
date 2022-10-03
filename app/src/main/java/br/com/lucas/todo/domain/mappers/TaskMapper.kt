package br.com.lucas.todo.domain.mappers

import br.com.lucas.todo.core.ext.*
import br.com.lucas.todo.core.util.DateUtil
import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.mappers.base.BaseMapper
import java.util.*

class TaskMapper: BaseMapper<TaskEntity, Task> {

    override fun mapToDomainModel(entity: TaskEntity): Task {
        return Task(
            uuid = entity.uuid,
            taskTitle = entity.taskTitle,
            taskDescription = entity.taskDescription,
            taskDate = DateUtil.formatLongToStringDate(entity.taskDate),
            taskHour = entity.taskTime.toHour(),
            taskMinute = entity.taskTime.toMinute(),
            isSelected = entity.isSelected
        )
    }

    override fun mapToEntity(domainModel: Task): TaskEntity {
        return TaskEntity(
            uuid = domainModel.uuid ?: UUID.randomUUID(),
            taskTitle = domainModel.taskTitle,
            taskDescription = domainModel.taskDescription,
            taskDate = DateUtil.formatStringDateToLong(domainModel.taskDate),
            taskTime = formatHoursAndMinutesToIntTime(domainModel.taskHour, domainModel.taskMinute),
            isSelected = domainModel.isSelected
        )
    }

    override fun mapToDomainModels(listEntity: List<TaskEntity>): List<Task> {
        return listEntity.map { mapToDomainModel(it) }
    }

    override fun mapToEntities(listDomainModel: List<Task>): List<TaskEntity> {
        return listDomainModel.map { mapToEntity(it) }
    }
}