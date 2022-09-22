package br.com.lucas.todo.presentation.listTask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.databinding.FragmentListTaskBinding
import br.com.lucas.todo.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListTaskFragment : BaseFragment<FragmentListTaskBinding, ListTaskViewModel>() {

    private val adapter by lazy { ListTaskAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.taskList.observe(
            viewLifecycleOwner
        ) { tasks ->
            adapter.addTask(tasks)
            checkEmptyStateAfterAddTasks()
        }
        setupList()
        context?.let { insertListeners(it) }
    }


    private fun checkEmptyStateAfterAddTasks() {
        if (viewModel.isTaskListEmpty() == true) {
            binding.includeState.emptyState.visibility = View.VISIBLE
            binding.recyclerViewTasks.visibility = View.INVISIBLE
        } else {
            binding.includeState.emptyState.visibility = View.GONE
            binding.recyclerViewTasks.visibility = View.VISIBLE
        }
    }

    private fun setupList() {
        binding.recyclerViewTasks.adapter = adapter
    }

    private fun insertListeners(context: Context) {
        binding.fabAddTask.setOnClickListener {
            it.findNavController().navigate(R.id.fromListTaskToEditTask, )
        }

        adapter.listenerLaunchInfoTask(binding.recyclerViewTasks)

        adapter.listenerEdit = {
            val task = Bundle().apply { putParcelable(TASK_TO_EDIT, it) }
            view?.findNavController()?.navigate(R.id.fromListTaskToEditTask, task)
        }
        adapter.listenerDelete = {
            viewModel.delete(it) {
                Toast.makeText(context, getString(R.string.successfully_deleted), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshScreen()
    }

    override fun setupViewBinding(layoutInflater: LayoutInflater) = FragmentListTaskBinding.inflate(layoutInflater)

    override fun setupViewModel() = viewModels<ListTaskViewModel>()

}