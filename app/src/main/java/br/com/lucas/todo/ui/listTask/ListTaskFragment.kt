package br.com.lucas.todo.ui.listTask

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.databinding.FragmentListTaskBinding
import br.com.lucas.todo.ui.base.BaseFragment


class ListTaskFragment : BaseFragment<FragmentListTaskBinding>(FragmentListTaskBinding::inflate) {

    lateinit var viewModel: ListTaskViewModel
    private val adapter by lazy { ListTaskAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            viewModel = ListTaskViewModel(it)
        }
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

        adapter.listenerLaunchInfoTask(context, binding.recyclerViewTasks)

        adapter.listenerEdit = {
            val task = Bundle().apply { putSerializable(TASK_TO_EDIT, it) }
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
}