package br.com.tasks.presentation.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import br.com.core.Constants.TASK_TO_EDIT
import br.com.tasks.R
import br.com.core.base.fragment.BaseFragment
import br.com.tasks.databinding.FragmentListTaskBinding
import br.com.uikit.adapter.GenericAdapter
import br.com.uikit.adapter.callbacks.ListTaskAdapterCallback
import br.com.uikit.adapter.model.Task
import br.com.uikit.components.GenericDialog
import br.com.uikit.extensions.gone
import br.com.uikit.extensions.visible

class ListTaskFragment : BaseFragment<FragmentListTaskBinding, ListTaskViewModel>(FragmentListTaskBinding::inflate),
    ListTaskAdapterCallback {

    private val adapter by lazy { GenericAdapter(this) }

    override fun onResume() {
        super.onResume()
        viewModel.refreshScreen { showToast(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarMenu()
        setupRecyclerView()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.taskList.observe(viewLifecycleOwner) { taskList ->
            adapter.submitList(taskList)
            checkEmptyState()
        }


        viewModel.hasSelectedTasks.observe(viewLifecycleOwner) { hasSelectedTasks ->
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewTasks.adapter = adapter
        binding.recyclerViewTasks.itemAnimator = null
    }

    private fun setupListeners() {
        binding.fabAddTask.setOnClickListener {
            it.findNavController().navigate(R.id.fromListTaskToEditTask)
        }
    }

    private fun setupToolbarMenu() {

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

    private fun showDeleteSelectedTasksConfirmationDialog() {
        context?.let { ctx ->
            GenericDialog.Builder(ctx)
                .setTitle(resources.getQuantityString(
                        R.plurals.delete_selected_tasks_dialog_title,
                        viewModel.selectedTasksQuantity,
                        viewModel.selectedTasksQuantity
                    ))
                .setBodyMessage(resources.getQuantityString(
                        R.plurals.delete_selected_tasks_dialog_body_message,
                        viewModel.selectedTasksQuantity,
                        viewModel.selectedTasksQuantity
                    ))
                .setPositiveButtonText(getString(R.string.yes))
                .setOnPositiveButtonClickListener { viewModel.deleteSelectedTasks { showToast(it) } }
                .setNegativeButtonText(getString(R.string.no))
                .build()
        }
    }

    override fun onTaskClicked(task: Task) {
        view?.findNavController()
            ?.navigate(R.id.fromListTaskToEditTask, bundleOf(TASK_TO_EDIT to task))
    }

    override fun syncSelection(isSelected: Boolean, task: Task) {
        viewModel.syncSelection(isSelected, task)
    }
}