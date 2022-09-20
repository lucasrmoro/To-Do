package br.com.lucas.todo.ui.listTask

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.todo.R
import br.com.lucas.todo.databinding.ActivityListTaskBinding
import br.com.lucas.todo.ui.editTask.EditTaskActivity


class ListTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListTaskBinding
    lateinit var viewModel: ListTaskViewModel
    private val adapter by lazy { ListTaskAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTaskBinding.inflate(layoutInflater)
        viewModel = ListTaskViewModel(application)
        setContentView(binding.root)

        viewModel.taskList.observe(
            this
        ) { tasks ->
            adapter.addTask(tasks)
            checkEmptyStateAfterAddTasks()
        }
        setupList()
        insertListeners(this)
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
            EditTaskActivity.launchNewTaskScreen(context)
        }

        adapter.listenerLaunchInfoTask(context, binding.recyclerViewTasks)

        adapter.listenerEdit = {
            EditTaskActivity.launchEditTaskScreen(context, it)
        }
        adapter.listenerDelete = {
            viewModel.delete(it) {
                Toast.makeText(this, getString(R.string.successfully_deleted), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshScreen()
    }
}