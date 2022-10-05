package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.core.util.DateUtil
import br.com.lucas.todo.domain.model.Task
import javax.inject.Inject

class GetTasksByDateUseCase @Inject constructor(
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