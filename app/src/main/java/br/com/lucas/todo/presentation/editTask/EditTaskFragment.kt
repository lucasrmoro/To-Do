package br.com.lucas.todo.presentation.editTask

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.ext.errorState
import br.com.lucas.todo.core.ext.formatLongToStringDate
import br.com.lucas.todo.core.ext.getHoursAndMinutesFormatted
import br.com.lucas.todo.core.ext.getStringText
import br.com.lucas.todo.databinding.FragmentEditTaskBinding
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.base.fragment.BaseFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditTaskFragment : BaseFragment<FragmentEditTaskBinding>(FragmentEditTaskBinding::inflate) {

    private val viewModel: EditTaskViewModel by viewModels()

    private val taskToEdit by lazy { arguments?.getParcelable<Task>(TASK_TO_EDIT) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewAccordingToEditMode(taskToEdit)
        setupObservers()
        setupListeners()
    }

    private fun setupViewAccordingToEditMode(task: Task?) = with(binding) {
        task?.run {
            viewModel.setEditModeEnabled()
            btnCreateTask.text = getString(R.string.edit_task)
            edtTitle.setText(taskTitle)
            edtDescription.setText(taskDescription)
            edtDate.setText(taskDate)
            edtHour.setText(taskTime)
        }
    }

    private fun setupObservers() = with(binding) {
        viewModel.isTaskTitleValid.observe(viewLifecycleOwner) { isValid ->
            edtTitleLayout.errorState(isValid.not(), R.string.required_field)
        }

        viewModel.isTaskTimeValid.observe(viewLifecycleOwner) { isValid ->
            edtHourLayout.errorState(isValid.not(), R.string.required_field)
        }

        viewModel.isTaskDateValid.observe(viewLifecycleOwner) { isValid ->
            edtDateLayout.errorState(isValid.not(), R.string.required_field)
        }

        edtTitle.doAfterTextChanged { viewModel.checkTaskTitleIsValid(it.toString()) }

        edtDate.doAfterTextChanged { viewModel.checkTaskDateIsValid(it.toString()) }

        edtHour.doAfterTextChanged { viewModel.checkTaskTimeIsValid(it.toString()) }
    }

    private fun setupListeners() {
        binding.edtDate.setOnClickListener { showDatePicker() }
        binding.edtHour.setOnClickListener { showTimePicker() }
        binding.btnCreateTask.setOnClickListener { view ->
            with(binding) {
                viewModel.onSaveEvent(
                    task = Task(
                        edtTitle.getStringText(),
                        edtDescription.getStringText(),
                        edtDate.getStringText(),
                        edtHour.getStringText(),
                        taskToEdit?.uuid
                    ),
                    toast = {
                        showToast(it)
                        view.findNavController().popBackStack()
                    },
                    onFieldsNotValid = { showToast(R.string.fill_all_required_fields) }
                )
            }
        }

        binding.btnCancelTask.setOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }

    private fun showDatePicker() {
        val minDate =
            CalendarConstraints.Builder().setStart(Calendar.getInstance().timeInMillis).run {
                setValidator(DateValidatorPointForward.now())
            }
        MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(minDate.build())
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    binding.edtDate.setText(it.formatLongToStringDate())
                }
            }.show(parentFragmentManager, DATE_PICKER_TAG)
    }

    private fun showTimePicker() {
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("          ")
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    binding.edtHour.setText(getHoursAndMinutesFormatted())
                }
            }.show(parentFragmentManager, TIME_PICKER_TAG)
    }

    companion object {
        private const val DATE_PICKER_TAG = "DATE_PICKER_TAG"
        private const val TIME_PICKER_TAG = "TIME_PICKER_TAG"
    }
}