package br.com.tasks.domain.useCases

import br.com.core.util.DateUtil
import br.com.uikit.adapter.model.Task

class GetTasksByDateUseCase constructor(
    private val dateUtil: DateUtil
) {

    fun execute(
        taskList: List<Task>,
        dayDesired: String?,
        monthDesired: String?,
        yearDesired: String?
    ) = taskList.filter { task ->
        task.date == dateUtil.getStringDateBy(dayDesired, monthDesired, yearDesired)
    }

}