package br.com.lucas.santanderbootcamp.todolist.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityListTaskBinding


private lateinit var binding: ActivityListTaskBinding
lateinit var viewModel: ListTaskViewModel

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

        configureList()
        insertListeners()
    }

    private fun insertListeners() {
        binding.fabAddTask.setOnClickListener { _ ->
            EditTaskActivity.launchNewTaskScreen(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
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