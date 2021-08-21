package br.com.lucas.santanderbootcamp.todolist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityEditTaskBinding
import br.com.lucas.santanderbootcamp.todolist.extensions.formatDateToPtBr
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.sql.Time
import java.util.*

class EditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun insertListeners(){
        binding.edtDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                val selectedDate = Date(it + offset).formatDateToPtBr()
                binding.edtDate.setText(selectedDate)
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.edtHour.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("          ")
                .build()
            timePicker.addOnPositiveButtonClickListener {
                binding.edtHour.setText("${timePicker.hour} ${timePicker.minute}")
            }
            timePicker.show(supportFragmentManager, "HOUR_PICKER_TAG")
        }

        binding.btnCreateTask.setOnClickListener{

        }

        binding.btnCancelTask.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun launchNewTaskScreen(context: Context) {
            val intent = Intent(context, EditTaskActivity::class.java)
            context.startActivity(intent)
        }
    }
}