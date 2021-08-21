package br.com.lucas.santanderbootcamp.todolist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityEditTaskBinding
import br.com.lucas.santanderbootcamp.todolist.core.extensions.formatDateToPtBr
import br.com.lucas.santanderbootcamp.todolist.core.extensions.getColorResCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class EditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTaskBinding
    private lateinit var viewModel: EditTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        viewModel = EditTaskViewModel()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.isTaskTitleValid.observe(this){
            if(it == false){
                binding.edtTitle.setTextColor(Color.RED)
            } else {
                binding.edtTitle.setTextColor(this.getColorResCompat(android.R.attr.textColorPrimary))
            }
        }

        viewModel.isTaskHourValid.observe(this){
            if(it == false){
                binding.edtHour.setTextColor(Color.RED)
            } else {
                binding.edtHour.setTextColor(Color.BLACK)
            }
        }

        viewModel.isTaskDateValid.observe(this){
            if(it == false){
                binding.edtDate.setTextColor(Color.RED)
            } else {
                binding.edtDate.setTextColor(Color.BLACK)
            }
        }

        binding.edtTitle.doAfterTextChanged {
            viewModel.checkTaskTitleIsValid(it.toString())
        }

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
                viewModel.checkTaskDateIsValid(selectedDate)
                binding.edtDate.setText(selectedDate)
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.edtHour.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("          ")
                .build()
            timePicker.show(supportFragmentManager, "HOUR_PICKER_TAG")
            timePicker.addOnPositiveButtonClickListener {
                val hourSelected = "${timePicker.hour}:${timePicker.minute}"
                viewModel.checkTaskHourIsValid(hourSelected)
                binding.edtHour.setText(hourSelected)
            }
        }

        binding.btnCreateTask.setOnClickListener{
            viewModel.onSaveEvent(context = this,
                taskTitle = binding.edtTitle.text.toString(),
                taskDescription = binding.edtDescription.text.toString(),
                taskDate = binding.edtDate.text.toString(),
                taskHour = binding.edtHour.text.toString(),
                closeScreen = { finish() })
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