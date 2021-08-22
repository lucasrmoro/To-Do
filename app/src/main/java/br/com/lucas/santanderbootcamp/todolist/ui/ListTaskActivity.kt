package br.com.lucas.santanderbootcamp.todolist.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityListTaskBinding


private lateinit var binding: ActivityListTaskBinding
lateinit var viewModel: ListTaskViewModel
private val adapter by lazy { ListTaskAdapter() }

class ListTaskActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTaskBinding.inflate(layoutInflater)
        viewModel = ListTaskViewModel(application)
        setContentView(binding.root)

        viewModel.taskList.observe(
            this
        ) { tasks ->
            (binding.recyclerViewTasks.adapter as ListTaskAdapter).addTask(tasks)
        }

        binding.recyclerViewTasks.adapter = adapter
        insertListeners(this)
    }

    private fun insertListeners(context: Context) {
        binding.fabAddTask.setOnClickListener { _ ->
            EditTaskActivity.launchNewTaskScreen(context)
        }
        adapter.listenerEdit = {
            EditTaskActivity.launchEditTaskScreen(context, it)
        }

        adapter.listenerDelete = {
            viewModel.delete(it){
                Toast.makeText(this, getString(R.string.successfully_deleted), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshScreen()
    }
}
