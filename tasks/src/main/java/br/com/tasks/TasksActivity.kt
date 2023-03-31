package br.com.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tasks.core.di.TasksInitializer
import br.com.tasks.databinding.ActivityTasksBinding

class TasksActivity: AppCompatActivity() {

    private val binding by lazy { ActivityTasksBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TasksInitializer.init()
        setContentView(binding.root)
    }

}