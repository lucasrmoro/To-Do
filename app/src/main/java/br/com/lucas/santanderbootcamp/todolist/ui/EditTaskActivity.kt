package br.com.lucas.santanderbootcamp.todolist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doAfterTextChanged
import br.com.lucas.santanderbootcamp.todolist.R
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

    @SuppressLint("ResourceType")
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
//                binding.edtTitleLayout.boxStrokeColor =
            } else {
                binding.edtTitle.setTextColor(this.getColorResCompat(android.R.attr.textColorPrimary))
//                binding.edtTitleLayout.setBoxBackgroundColorResource(this.getColorResCompat(android.R.attr.textColorPrimary))
            }
        }

        viewModel.isTaskHourValid.observe(this){
            if(it == false){
//                binding.edtHourLayout.boxStrokeColor = this.getColorResCompat(android.R.attr.textColorPrimary)
            } else {
//                binding.edtHourLayout.boxStrokeColor = this.getColorResCompat(android.R.attr.textColorPrimary)
            }
        }

        viewModel.isTaskDateValid.observe(this){
            if(it == false){
//                binding.edtDate.setTextColor(Color.RED)
            } else {
//                binding.edtDate.setTextColor(Color.BLACK)
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
                val hours = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minutes = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hourSelected = "$hours:$minutes"
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