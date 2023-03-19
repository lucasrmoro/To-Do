package br.com.lucas.todo.domain.useCases

import br.com.lucas.todo.core.providers.date.DateProvider
import br.com.lucas.todo.domain.model.Task
import javax.inject.Inject

class GetTasksByDateUseCase @Inject constructor(
    private val dateProvider: DateProvider
) {

    fun execute(
        taskList: List<Task>,
        dayDesired: String?,
        monthDesired: String?,
        yearDesired: String?
    ) = taskList.filter { task ->
        task.date == dateProvider.getStringDateBy(dayDesired, monthDesired, yearDesired)
    }

}