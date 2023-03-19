package br.com.lucas.todo.domain.mappers

import br.com.lucas.todo.core.ext.*
import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.core.base.mapper.BaseMapper
import br.com.lucas.todo.core.providers.date.DateProvider
import br.com.lucas.todo.domain.model.Task
import java.util.*
import javax.inject.Inject

class TaskMapper @Inject constructor(
    private val dateProvider: DateProvider
): BaseMapper<TaskEntity, Task> {

    override fun mapToDomainModel(entity: TaskEntity): Task {
        return Task(
            uuid = entity.uuid,
            title = entity.title,
            description = entity.description,
            date = dateProvider.formatLongToStringDate(entity.date),
            hour = entity.time.toHour(),
            minute = entity.time.toMinute(),
            isSelected = entity.isSelected
        )
    }

    override fun mapToEntity(domainModel: Task): TaskEntity {
        return TaskEntity(
            uuid = domainModel.uuid ?: UUID.randomUUID(),
            title = domainModel.title,
            description = domainModel.description,
            date = dateProvider.formatStringDateToLong(domainModel.date),
            time = formatHoursAndMinutesToIntTime(domainModel.hour, domainModel.minute),
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