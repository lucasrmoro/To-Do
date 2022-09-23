package br.com.lucas.todo.presentation.infoTask

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.ext.formatStringDateToFullDate
import br.com.lucas.todo.databinding.FragmentInfoTaskBinding
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoTaskFragment : BaseFragment<FragmentInfoTaskBinding>(FragmentInfoTaskBinding::inflate) {

    private val task by lazy { arguments?.getParcelable<Task>(TASK_TO_EDIT) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {
        task?.run {
            tvTaskTitle.text = taskTitle
            if (taskDescription.isBlank()) {
                tvTaskDescription.text = getString(R.string.description_not_informed)
                tvTaskDescription.setTypeface(null, Typeface.ITALIC)
            } else {
                tvTaskDescription.text = taskDescription
            }
            tvTaskDate.text = taskDate.formatStringDateToFullDate()
            tvTaskHour.text = taskTime
        }
    }
}