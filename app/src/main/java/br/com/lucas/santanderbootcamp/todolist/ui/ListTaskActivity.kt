package br.com.lucas.santanderbootcamp.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityListTaskBinding

private lateinit var binding: ActivityListTaskBinding

class ListTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddTask.setOnClickListener { view ->
            EditTaskActivity.launchNewTaskScreen(this)
        }
    }
}