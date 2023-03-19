package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.core.providers.date.DateProvider
import br.com.lucas.todo.domain.model.DayHeader
import br.com.lucas.todo.domain.model.MonthHeader
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.model.YearHeader
import br.com.lucas.todo.domain.model.adapter.AdapterItem
import javax.inject.Inject

class GetTasksCategorizedByDateUseCase @Inject constructor(
    private val getTasksByDateUseCase: GetTasksByDateUseCase,
    private val dateProvider: DateProvider
) {

    fun execute(taskList: List<Task>): List<AdapterItem> {
        val taskListCategorizedByDate = mutableListOf<AdapterItem>()
        val datesOfTasks = taskList.map { it.date }.toSet().toList()

        dateProvider.getYearsFrom(datesOfTasks).forEach { year ->
            if (dateProvider.isCurrentYear(year).not())
                taskListCategorizedByDate.add(YearHeader(dateProvider.getFormattedYear(year)))

            dateProvider.getMonthsFrom(datesOfTasks, year).forEach { month ->
                if (dateProvider.isCurrentMonth(month, year).not())
                    taskListCategorizedByDate.add(
                        MonthHeader(dateProvider.getFormattedMonthFrom(month, year))
                    )

                dateProvider.getDaysFrom(datesOfTasks, month, year).forEach { day ->
                    taskListCategorizedByDate.add(
                        DayHeader(dateProvider.getFormattedDay(day, month, year))
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