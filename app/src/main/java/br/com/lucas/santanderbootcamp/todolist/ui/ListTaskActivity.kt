package br.com.lucas.santanderbootcamp.todolist.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityListTaskBinding

private lateinit var binding: ActivityListTaskBinding
private lateinit var viewModel: ListTaskViewModel

class ListTaskActivity : AppCompatActivity() {

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

        binding.fabAddTask.setOnClickListener { _ ->
            EditTaskActivity.launchNewTaskScreen(this)
        }
        configureList()
    }

    private fun configureList() {
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = ListTaskAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshScreen()
    }
}