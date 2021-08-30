package br.com.lucas.santanderbootcamp.todolist.ui.infoTask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.core.extensions.convertLongToDate
import br.com.lucas.santanderbootcamp.todolist.core.extensions.convertIntTimeToString
import br.com.lucas.santanderbootcamp.todolist.database.Task
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityInfoTaskBinding
import br.com.lucas.santanderbootcamp.todolist.ui.editTask.EditTaskActivity

class InfoTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoTaskBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val task: Task? = intent.getSerializableExtra(EditTaskActivity.TASK_NAME_KEY) as? Task

        if (task != null) {
            binding.tvTaskTitle.text = task.taskTitle
            if (task.taskDescription.isBlank()) {
                binding.tvTaskDescription.text = getString(R.string.description_not_informed)
                binding.tvTaskDescription.setTypeface(null, Typeface.ITALIC)
            } else {
                binding.tvTaskDescription.text = task.taskDescription
            }
            binding.tvTaskDate.text = task.taskDate.convertLongToDate()
            binding.tvTaskHour.text = task.taskTime.convertIntTimeToString()
        }
    }

    companion object {
        fun launchInfoTaskActivity(context: Context, task: Task) {
            val intent = Intent(context, InfoTaskActivity::class.java)
            intent.putExtra(EditTaskActivity.TASK_NAME_KEY, task)
            context.startActivity(intent)
        }
    }
}