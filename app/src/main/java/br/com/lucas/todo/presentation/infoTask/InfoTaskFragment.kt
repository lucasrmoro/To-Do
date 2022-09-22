package br.com.lucas.todo.presentation.infoTask

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.ext.convertIntTimeToString
import br.com.lucas.todo.core.ext.convertLongToFullDate
import br.com.lucas.todo.data.db.entities.Task
import br.com.lucas.todo.databinding.FragmentInfoTaskBinding
import br.com.lucas.todo.presentation.base.BaseFragment
import br.com.lucas.todo.presentation.base.DummyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoTaskFragment :
    BaseFragment<FragmentInfoTaskBinding, DummyViewModel>() {

    private val task by lazy { arguments?.getParcelable<Task>(TASK_TO_EDIT) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            task?.run {
                tvTaskTitle.text = taskTitle
                if (taskDescription.isBlank()) {
                    tvTaskDescription.text = getString(R.string.description_not_informed)
                    tvTaskDescription.setTypeface(null, Typeface.ITALIC)
                } else {
                    tvTaskDescription.text = taskDescription
                }
                tvTaskDate.text = taskDate.convertLongToFullDate()
                tvTaskHour.text = taskTime.convertIntTimeToString()
            }
        }
    }

    override fun setupViewBinding(layoutInflater: LayoutInflater) =
        FragmentInfoTaskBinding.inflate(layoutInflater)

    override fun setupViewModel() = viewModels<DummyViewModel>()

}