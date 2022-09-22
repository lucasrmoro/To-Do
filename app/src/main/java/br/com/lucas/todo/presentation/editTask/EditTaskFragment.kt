package br.com.lucas.todo.presentation.editTask

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.ext.convertIntTimeToString
import br.com.lucas.todo.core.ext.convertLongToCompactDate
import br.com.lucas.todo.core.ext.formatDateToString
import br.com.lucas.todo.core.ext.getColorResCompat
import br.com.lucas.todo.data.db.entities.Task
import br.com.lucas.todo.databinding.FragmentEditTaskBinding
import br.com.lucas.todo.presentation.base.BaseFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditTaskFragment :
    BaseFragment<FragmentEditTaskBinding, EditTaskViewModel>() {

    private val task by lazy { arguments?.getSerializable(TASK_TO_EDIT) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verifyingThatTaskAlreadyExistsAndCatchDataFromDB(task as Task?)
        observingAndVerifyingThatAllFieldsAreFilled()
        insertListeners()
    }

    private fun verifyingThatTaskAlreadyExistsAndCatchDataFromDB(task: Task?) {
        if (task != null) {
            viewModel.setup(task)
            binding.btnCreateTask.text = getString(R.string.edit_task)
            binding.edtTitle.setText("${viewModel.task?.taskTitle}")
            binding.edtTitle.setSelection(viewModel.task?.taskTitle?.length ?: 0)
            binding.edtDescription.setText("${viewModel.task?.taskDescription}")
            binding.edtDate.setText("${viewModel.task?.taskDate?.convertLongToCompactDate()}")
            binding.edtHour.setText(viewModel.task?.taskTime?.convertIntTimeToString())
        }
    }

    private fun observingAndVerifyingThatAllFieldsAreFilled() {
        viewModel.isTaskTitleValid.observe(viewLifecycleOwner) { isValid ->
            if (isValid) {
                context?.getColorResCompat(android.R.attr.textColorPrimary)?.let {
                    binding.edtTitle.setTextColor(it)
                }
                binding.edtTitleLayout.error = null
            } else {
                binding.edtTitle.setTextColor(Color.RED)
                binding.edtTitleLayout.error = getString(R.string.required_field)
            }
        }

        viewModel.isTaskTimeValid.observe(viewLifecycleOwner) { isValid ->
            if (isValid) {
                binding.edtHourLayout.error = null
            } else {
                binding.edtHourLayout.error = getString(R.string.required_field)
            }
        }


        viewModel.isTaskDateValid.observe(viewLifecycleOwner) { isValid ->
            if (isValid) {
                binding.edtDateLayout.error = null
            } else {
                binding.edtDateLayout.error = getString(R.string.required_field)
            }
        }

        binding.edtTitle.doAfterTextChanged {
            viewModel.checkTaskTitleIsValid(it.toString())
        }

        binding.edtDate.doAfterTextChanged {
            viewModel.checkTaskDateIsValid(it.toString())
        }

        binding.edtHour.doAfterTextChanged {
            viewModel.checkTaskTimeIsValid()
        }
    }

    private fun insertListeners() {
        binding.edtDate.setOnClickListener {
            buildDatePicker()
        }

        binding.edtHour.setOnClickListener {
            buildTimePicker()
        }

        binding.btnCreateTask.setOnClickListener { v ->
            context?.let {
                viewModel.onSaveEvent(context = it,
                    taskTitle = binding.edtTitle.text.toString(),
                    taskDescription = binding.edtDescription.text.toString(),
                    taskDate = binding.edtDate.text.toString(),
                    closeScreen = { v.findNavController().popBackStack() })
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

    override fun setupViewBinding(layoutInflater: LayoutInflater) =
        FragmentEditTaskBinding.inflate(layoutInflater)

    override fun setupViewModel() = viewModels<EditTaskViewModel>()
}