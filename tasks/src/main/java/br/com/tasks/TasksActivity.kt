package br.com.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tasks.databinding.ActivityTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksActivity: AppCompatActivity() {

    private val binding by lazy { ActivityTasksBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}