package br.com.lucas.todo.ui.editTask

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.extensions.*
import br.com.lucas.todo.database.Task
import br.com.lucas.todo.databinding.FragmentEditTaskBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditTaskViewModel
    private val task by lazy { arguments?.getSerializable(TASK_NAME_KEY) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = EditTaskViewModel()
        showAppBar()
        verifyingThatTaskAlreadyExistsAndCatchDataFromDB(task as Task?)
        observingAndVerifyingThatAllFieldsAreFilled()
        insertListeners()
    }

    private fun verifyingThatTaskAlreadyExistsAndCatchDataFromDB(task: Task?) {
        if (task != null) {
            viewModel.setup(task)
            binding.btnCreateTask.text = getString(R.string.edit_task)
            binding.toolbar.title = getString(R.string.edit_task)
            binding.edtTitle.setText("${viewModel.task?.taskTitle}")
            binding.edtTitle.setSelection(viewModel.task?.taskTitle?.length ?: 0)
            binding.edtDescription.setText("${viewModel.task?.taskDescription}")
            binding.edtDate.setText("${viewModel.task?.taskDate?.convertLongToCompactDate()}")
            binding.edtHour.setText(viewModel.task?.taskTime?.convertIntTimeToString())
        }
    }

    private fun observingAndVerifyingThatAllFieldsAreFilled() {
        viewModel.isTaskTitleValid.observe(viewLifecycleOwner) {
            if (it == false) {
                binding.edtTitle.setTextColor(Color.RED)
                binding.edtTitleLayout.error = getString(R.string.required_field)
            } else {
                context?.getColorResCompat(android.R.attr.textColorPrimary)
                    ?.let { it1 -> binding.edtTitle.setTextColor(it1) }
                binding.edtTitleLayout.error = null
            }
        }

        viewModel.isTaskTimeEmpty.observe(viewLifecycleOwner) {
            if (it == false) binding.edtHourLayout.error = getString(R.string.required_field)
            else binding.edtHourLayout.error = null
        }


        viewModel.isTaskDateEmpty.observe(viewLifecycleOwner) {
            if (it == false) binding.edtDateLayout.error = getString(R.string.required_field)
            else binding.edtDateLayout.error = null
        }

        binding.edtTitle.doAfterTextChanged {
            viewModel.checkTaskTitleIsValid(it.toString())
        }

        binding.edtDate.doAfterTextChanged {
            viewModel.checkTaskDateIsEmpty(it.toString())
        }

        binding.edtHour.doAfterTextChanged {
            viewModel.checkTaskTimeIsEmpty()
        }
    }

    private fun insertListeners() {
        binding.edtDate.setOnClickListener { view ->
            buildDatePicker()
        }

        binding.edtHour.setOnClickListener { view ->
            buildTimePicker()
        }

        binding.btnCreateTask.setOnClickListener { view ->
            context?.let {
                viewModel.onSaveEvent(context = it,
                    taskTitle = binding.edtTitle.text.toString(),
                    taskDescription = binding.edtDescription.text.toString(),
                    taskDate = binding.edtDate.text.toString(),
                    closeScreen = { view.findNavController().popBackStack() })
            }
        }

        binding.btnCancelTask.setOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }

    private fun buildDatePicker() {
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
        datePicker.show(parentFragmentManager, "DATE_PICKER_TAG")
    }

    private fun buildTimePicker() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("          ")
            .build()
        timePicker.show(parentFragmentManager, "HOUR_PICKER_TAG")
        timePicker.addOnPositiveButtonClickListener {
            viewModel.convertHourAndMinutesToFullTime(timePicker.hour, timePicker.minute)
            binding.edtHour.setText(viewModel.totalTaskTime?.convertIntTimeToString())
        }
    }

    companion object {
        fun launchNewTaskScreen(context: Context) {
            val intent = Intent(context, EditTaskFragment::class.java)
            context.startActivity(intent)
        }

        fun launchEditTaskScreen(context: Context, task: Task?) {
            val intent = Intent(context, EditTaskFragment::class.java)
            intent.putExtra(TASK_NAME_KEY, task)
            context.startActivity(intent)
        }

        const val TASK_NAME_KEY = "task"
    }
}