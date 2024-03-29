package br.com.lucas.todo.ui.infoTask

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.extensions.convertIntTimeToString
import br.com.lucas.todo.core.extensions.convertLongToFullDate
import br.com.lucas.todo.database.Task
import br.com.lucas.todo.databinding.FragmentInfoTaskBinding
import br.com.lucas.todo.ui.base.BaseFragment
import br.com.lucas.todo.ui.base.DummyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoTaskFragment :
    BaseFragment<FragmentInfoTaskBinding, DummyViewModel>() {

    private val task by lazy { arguments?.getSerializable(TASK_TO_EDIT) as Task? }

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