package br.com.lucas.todo.presentation.editTask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.domain.useCases.InsertTaskUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import br.com.lucas.todo.rules.CoroutinesTestRule
import br.com.lucas.todo.rules.TimberRule
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class EditTaskViewModelTest {

    @get: Rule
    val coroutineTestRule = CoroutinesTestRule()

    @get: Rule
    val timberRule = TimberRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val insertTaskUseCase: InsertTaskUseCase = mockk(relaxed = true)
    private val updateTaskUseCase: UpdateTaskUseCase = mockk(relaxed = true)
    private lateinit var viewModel: EditTaskViewModel

    @Before
    fun setup() {
        viewModel = EditTaskViewModel(insertTaskUseCase, updateTaskUseCase)
    }

    @Test
    fun `save new task - success`() {
        runTest {
            viewModel.onSaveEvent(
                taskTitle = "titleTask1",
                taskHour = "14",
                taskMinute = "10",
                taskDescription = "descriptionTask1",
                taskDate = "25/10/2022"
            )

            advanceUntilIdle()
            coVerify(exactly = 1) {
                insertTaskUseCase.execute(
                    Task(
                        taskTitle = "titleTask1",
                        taskHour = "14",
                        taskMinute = "10",
                        taskDescription = "descriptionTask1",
                        taskDate = "25/10/2022"
                    )
                )
            }

        }
    }

    @Test
    fun `save new task - all fields invalid`() {
        runTest {
            viewModel.onSaveEvent(
                taskTitle = "",
                taskHour = "",
                taskMinute = "",
                taskDescription = "",
                taskDate = ""
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { insertTaskUseCase.execute(any()) }
        }
    }

    @Test
    fun `save new task - invalid title`() {
        runTest {
            viewModel.onSaveEvent(
                taskTitle = "tt",
                taskHour = "14",
                taskMinute = "10",
                taskDescription = "descriptionTask1",
                taskDate = "25/10/2022"
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { insertTaskUseCase.execute(any()) }
        }
    }

    @Test
    fun `save new task - invalid time`() {
        runTest {
            viewModel.onSaveEvent(
                taskTitle = "taskTitle",
                taskHour = "",
                taskMinute = "",
                taskDescription = "descriptionTask1",
                taskDate = "25/10/2022"
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { insertTaskUseCase.execute(any()) }
        }
    }

    @Test
    fun `save new task - invalid date`() {
        runTest {
            viewModel.onSaveEvent(
                taskTitle = "taskTitle",
                taskHour = "14",
                taskMinute = "10",
                taskDescription = "descriptionTask1",
                taskDate = ""
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { insertTaskUseCase.execute(any()) }
        }
    }

    @Test
    fun `save same task - success`() {
        runTest {
            val uuid = UUID.randomUUID()
            viewModel.setEditModeEnabled(
                Task(
                    uuid = uuid,
                    taskTitle = "titleTask1",
                    taskHour = "14",
                    taskMinute = "10",
                    taskDescription = "descriptionTask1",
                    taskDate = "25/10/2022"
                )
            )

            viewModel.onSaveEvent(
                taskTitle = "titleTask2",
                taskHour = "09",
                taskMinute = "05",
                taskDescription = "descriptionTask1",
                taskDate = "25/10/2022"
            )

            advanceUntilIdle()
            coVerify(exactly = 1) {
                updateTaskUseCase.execute(
                    Task(
                        uuid = uuid,
                        taskTitle = "titleTask2",
                        taskHour = "09",
                        taskMinute = "05",
                        taskDescription = "descriptionTask1",
                        taskDate = "25/10/2022"
                    )
                )
            }
        }
    }

    @Test
    fun `save same task - invalid title`() {
        runTest {
            val uuid = UUID.randomUUID()
            viewModel.setEditModeEnabled(
                Task(
                    uuid = uuid,
                    taskTitle = "titleTask1",
                    taskHour = "14",
                    taskMinute = "10",
                    taskDescription = "descriptionTask1",
                    taskDate = "25/10/2022"
                )
            )

            viewModel.onSaveEvent(
                taskTitle = "tt",
                taskHour = "09",
                taskMinute = "05",
                taskDescription = "descriptionTask1",
                taskDate = "25/10/2022"
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { updateTaskUseCase.execute(any()) }
        }
    }

    @Test
    fun `save same task - invalid time`() {
        runTest {
            val uuid = UUID.randomUUID()
            viewModel.setEditModeEnabled(
                Task(
                    uuid = uuid,
                    taskTitle = "titleTask1",
                    taskHour = "14",
                    taskMinute = "10",
                    taskDescription = "descriptionTask1",
                    taskDate = "25/10/2022"
                )
            )

            viewModel.onSaveEvent(
                taskTitle = "titleTaskTest2",
                taskHour = "",
                taskMinute = "",
                taskDescription = "descriptionTask1",
                taskDate = "25/10/2022"
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { updateTaskUseCase.execute(any()) }
        }
    }

    @Test
    fun `save same task - invalid date`() {
        runTest {
            val uuid = UUID.randomUUID()
            viewModel.setEditModeEnabled(
                Task(
                    uuid = uuid,
                    taskTitle = "titleTask1",
                    taskHour = "14",
                    taskMinute = "10",
                    taskDescription = "descriptionTask1",
                    taskDate = "25/10/2022"
                )
            )

            viewModel.onSaveEvent(
                taskTitle = "taskTitle22",
                taskHour = "09",
                taskMinute = "05",
                taskDescription = "descriptionTask1",
                taskDate = ""
            )

            advanceUntilIdle()
            coVerify(exactly = 0) { updateTaskUseCase.execute(any()) }
        }
    }
}