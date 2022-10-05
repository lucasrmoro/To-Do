package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.core.util.DateUtil
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.common.generic.adapter.model.AdapterItem
import br.com.lucas.todo.presentation.listTask.adapter.model.DayHeader
import br.com.lucas.todo.presentation.listTask.adapter.model.MonthHeader
import br.com.lucas.todo.presentation.listTask.adapter.model.YearHeader
import javax.inject.Inject

class GetTasksCategorizedByDateUseCase @Inject constructor(
    private val getTasksByDateUseCase: GetTasksByDateUseCase,
    private val dateUtil: DateUtil
) {

    fun execute(taskList: List<Task>): List<AdapterItem> {
        val taskListCategorizedByDate = mutableListOf<AdapterItem>()
        val datesOfTasks = taskList.map { it.date }.toSet().toList()

        dateUtil.getYearsFrom(datesOfTasks)?.forEach { year ->
            if (dateUtil.isCurrentYear(year).not())
                taskListCategorizedByDate.add(YearHeader(dateUtil.getFormattedYear(year)))

            dateUtil.getMonthsFrom(datesOfTasks, year)?.forEach { month ->
                if (dateUtil.isCurrentMonth(month, year).not())
                    taskListCategorizedByDate.add(
                        MonthHeader(dateUtil.getFormattedMonthFrom(month, year))
                    )

                dateUtil.getDaysFrom(datesOfTasks, month, year)?.forEach { day ->
                    taskListCategorizedByDate.add(
                        DayHeader(dateUtil.getFormattedDay(day, month, year))
                    )

                    val tasksFilteredByDate =
                        getTasksByDateUseCase.execute(taskList, day, month, year)
                    tasksFilteredByDate.forEach { taskListCategorizedByDate.add(it) }
                }
            }

        }
        return taskListCategorizedByDate
    }


}