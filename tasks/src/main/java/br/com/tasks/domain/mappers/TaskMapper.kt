package br.com.tasks.domain.mappers

import br.com.core.base.mapper.BaseMapper
import br.com.core.util.DateUtil
import br.com.tasks.data.db.entities.TaskEntity
import br.com.uikit.adapter.model.Task
import br.com.uikit.extensions.formatHoursAndMinutesToIntTime
import br.com.uikit.extensions.toHour
import br.com.uikit.extensions.toMinute
import java.util.*

class TaskMapper constructor(
    private val dateUtil: DateUtil
): BaseMapper<TaskEntity, Task> {

    override fun mapToDomainModel(entity: TaskEntity): Task {
        return Task(
            uuid = entity.uuid,
            title = entity.title,
            description = entity.description,
            date = dateUtil.formatLongToStringDate(entity.date),
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
            date = dateUtil.formatStringDateToLong(domainModel.date),
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