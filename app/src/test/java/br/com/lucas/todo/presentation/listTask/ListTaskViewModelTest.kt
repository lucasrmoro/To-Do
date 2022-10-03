package br.com.lucas.todo.presentation.listTask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.lucas.todo.domain.useCases.DeleteSelectedTasksUseCase
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase
import br.com.lucas.todo.rules.CoroutinesTestRule
import br.com.lucas.todo.rules.TimberRule
import br.com.lucas.todo.testProviders.MockedTasksProvider
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ListTaskViewModelTest {

    @get: Rule
    val coroutineTestRule = CoroutinesTestRule()

    @get: Rule
    val timberRule = TimberRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockedTasksProvider = MockedTasksProvider()
    private val getAllTaskUseCase: GetAllTasksUseCase = mockk(relaxed = true)
    private val deleteSelectedTasksUseCase: DeleteSelectedTasksUseCase = mockk(relaxed = true)
    private val updateTaskUseCase: UpdateTaskUseCase = mockk(relaxed = true)
    private lateinit var viewModel: ListTaskViewModel

    @Before
    fun setup() {
        viewModel = ListTaskViewModel(updateTaskUseCase, getAllTaskUseCase, deleteSelectedTasksUseCase)
    }

    @Test
    fun `get all tasks - error`() {
        runTest {
            coEvery { getAllTaskUseCase.execute() }.throws(NullPointerException())

            viewModel.refreshScreen()

            advanceUntilIdle()
            Truth.assertThat(viewModel.taskList.value).isNull()
            Truth.assertThat(viewModel.isTaskListEmpty()).isTrue()
        }
    }

    @Test
    fun `get all tasks - empty`() {
        runTest {
            coEvery { getAllTaskUseCase.execute() }.returns(emptyList())

            viewModel.refreshScreen()

            advanceUntilIdle()
            Truth.assertThat(viewModel.taskList.value).isEmpty()
            Truth.assertThat(viewModel.hasSelectedTasks.value).isFalse()
            Truth.assertThat(viewModel.isTaskListEmpty()).isTrue()
        }
    }

    @Test
    fun `get all tasks - no selected tasks`() {
        runTest {
            coEvery { getAllTaskUseCase.execute() }.returns(mockedTasksProvider.getTaskListSortedByDate())

            viewModel.refreshScreen()

            advanceUntilIdle()
            Truth.assertThat(viewModel.taskList.value)
                .isEqualTo(mockedTasksProvider.getTaskListSortedByDate())
            Truth.assertThat(viewModel.hasSelectedTasks.value).isFalse()
            Truth.assertThat(viewModel.isTaskListEmpty()).isFalse()
            Truth.assertThat(viewModel.selectedTasksQuantity).isEqualTo(0)
        }
    }

    @Test
    fun `get all tasks - has selected tasks`() {
        runTest {
            coEvery { getAllTaskUseCase.execute() }.returns(mockedTasksProvider.getTaskListSortedByDateWithFewSelected())

            viewModel.refreshScreen()

            advanceUntilIdle()
            Truth.assertThat(viewModel.taskList.value)
                .isEqualTo(mockedTasksProvider.getTaskListSortedByDateWithFewSelected())
            Truth.assertThat(viewModel.hasSelectedTasks.value).isTrue()
            Truth.assertThat(viewModel.isTaskListEmpty()).isFalse()
            Truth.assertThat(viewModel.selectedTasksQuantity).isEqualTo(2)
        }
    }

    @Test
    fun `delete selected tasks`() {
        runTest {
            viewModel.deleteSelectedTasks()

            advanceUntilIdle()
            coVerify(exactly = 1) { deleteSelectedTasksUseCase.execute() }
        }
    }

    @Test
    fun `selecting task`() {
        runTest {
            viewModel.syncSelection(true, mockedTasksProvider.task1.copy(isSelected = false))

            advanceUntilIdle()
            coVerify(exactly = 1) {
                updateTaskUseCase.execute(mockedTasksProvider.task1.copy(isSelected = true)
                )
            }
        }
    }

    @Test
    fun `deselecting task`() {
        runTest {
            viewModel.syncSelection(false, mockedTasksProvider.task1.copy(isSelected = true))

            advanceUntilIdle()
            coVerify(exactly = 1) {
                updateTaskUseCase.execute(
                    mockedTasksProvider.task1.copy(
                        isSelected = false
                    )
                )
            }
        }
    }
}