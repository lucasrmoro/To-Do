package br.com.lucas.todo.ui.listTask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.extensions.hideAppBar
import br.com.lucas.todo.databinding.FragmentListTaskBinding
import br.com.lucas.todo.ui.editTask.EditTaskFragment
import br.com.lucas.todo.ui.editTask.EditTaskFragment.Companion.TASK_NAME_KEY


class ListTaskFragment : Fragment() {

    private var _binding: FragmentListTaskBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ListTaskViewModel
    private val adapter by lazy { ListTaskAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAppBar()
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
            val task = Bundle().apply { putSerializable(TASK_NAME_KEY, it) }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}