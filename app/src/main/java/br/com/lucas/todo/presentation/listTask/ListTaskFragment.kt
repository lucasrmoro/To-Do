package br.com.lucas.todo.presentation.listTask

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.ext.gone
import br.com.lucas.todo.core.ext.visible
import br.com.lucas.todo.databinding.FragmentListTaskBinding
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListTaskFragment : BaseFragment<FragmentListTaskBinding>(FragmentListTaskBinding::inflate),
    ListTaskAdapterInterface {

    private val adapter by lazy { ListTaskAdapter(this) }
    private val viewModel: ListTaskViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.refreshScreen { showToast(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.taskList.observe(viewLifecycleOwner) { taskList ->
            adapter.setupList(taskList)
            checkEmptyState()
        }
    }

    private fun setupAdapter() {
        binding.recyclerViewTasks.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabAddTask.setOnClickListener {
            it.findNavController().navigate(R.id.fromListTaskToEditTask)
        }
    }

    private fun checkEmptyState() = with(binding) {
        if (viewModel.isTaskListEmpty()) {
            includeState.emptyState.visible()
            recyclerViewTasks.gone()
        } else {
            includeState.emptyState.gone()
            recyclerViewTasks.visible()
        }
    }

    override fun onEditOptionClicked(task: Task) {
        view?.findNavController()
            ?.navigate(R.id.fromListTaskToEditTask, bundleOf(TASK_TO_EDIT to task))
    }

    override fun onDeleteOptionClicked(task: Task) {
        viewModel.delete(task) { showToast(R.string.task_successfully_deleted) }
    }

    override fun onTaskClicked(task: Task) {
        view?.findNavController()
            ?.navigate(R.id.fromListTaskToInfoTask, bundleOf(TASK_TO_EDIT to task))
    }
}