package br.com.lucas.santanderbootcamp.todolist.ui.editTask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.core.extensions.convertLongToDate
import br.com.lucas.santanderbootcamp.todolist.core.extensions.formatDateToString
import br.com.lucas.santanderbootcamp.todolist.core.extensions.getColorResCompat
import br.com.lucas.santanderbootcamp.todolist.core.extensions.getHourFormatted
import br.com.lucas.santanderbootcamp.todolist.database.Task
import br.com.lucas.santanderbootcamp.todolist.databinding.ActivityEditTaskBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
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

        val task: Task? = intent.getSerializableExtra(TASK_NAME_KEY) as? Task

        if (task != null) {
            viewModel.setup(task)
            binding.btnCreateTask.text = getString(R.string.edit_task)
            binding.toolbar.title = getString(R.string.edit_task)
            binding.edtTitle.setText("${viewModel.task?.taskTitle}")
            binding.edtTitle.setSelection(viewModel.task?.taskTitle?.length ?: 0)
            binding.edtDescription.setText("${viewModel.task?.taskDescription}")
            binding.edtDate.setText("${viewModel.task?.taskDate?.convertLongToDate()}")
            binding.edtHour.setText("${viewModel.task?.taskHour}")
        }

        viewModel.isTaskTitleValid.observe(this) {
            if (it == false) {
                binding.edtTitle.setTextColor(Color.RED)
                binding.edtTitleLayout.error = getString(R.string.required_field)
            } else {
                binding.edtTitle.setTextColor(this.getColorResCompat(android.R.attr.textColorPrimary))
                binding.edtTitleLayout.error = null
            }
        }

        viewModel.isTaskHourEmpty.observe(this) {
            if (it == false) {
                binding.edtHourLayout.error = getString(R.string.required_field)
            } else {
                binding.edtHourLayout.error = null
            }
        }

        viewModel.isTaskDateEmpty.observe(this) {
            if (it == false) {
                binding.edtDateLayout.error = getString(R.string.required_field)
            } else {
                binding.edtDateLayout.error = null
            }
        }

        binding.edtTitle.doAfterTextChanged {
            viewModel.checkTaskTitleIsValid(it.toString())
        }

        binding.edtDate.doAfterTextChanged {
            viewModel.checkTaskDateIsEmpty(it.toString())
        }

        binding.edtHour.doAfterTextChanged {
            viewModel.checkTaskHourIsEmpty(it.toString())
        }

        insertListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun insertListeners() {
        binding.edtDate.setOnClickListener {
            val today = Calendar.getInstance().timeInMillis
            val constraints = CalendarConstraints.Builder().setStart(today)
            val validator = DateValidatorPointForward.now()
            constraints.setValidator(validator)
            val datePicker =
                MaterialDatePicker.Builder.datePicker().setCalendarConstraints(constraints.build())
                    .build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                val selectedDate = Date(it + offset)
                binding.edtDate.setText(selectedDate.formatDateToString())
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
                binding.edtHour.setText(timePicker.getHourFormatted())
            }
        }

        binding.btnCreateTask.setOnClickListener {
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

        fun launchEditTaskScreen(context: Context, task: Task?) {
            val intent = Intent(context, EditTaskActivity::class.java)
            intent.putExtra(TASK_NAME_KEY, task)
            context.startActivity(intent)
        }

        const val TASK_NAME_KEY = "task"
    }
}