package br.com.lucas.todo.ui.infoTask

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lucas.todo.R
import br.com.lucas.todo.core.extensions.convertIntTimeToString
import br.com.lucas.todo.core.extensions.convertLongToFullDate
import br.com.lucas.todo.core.extensions.showAppBar
import br.com.lucas.todo.database.Task
import br.com.lucas.todo.databinding.FragmentInfoTaskBinding
import br.com.lucas.todo.ui.editTask.EditTaskFragment.Companion.TASK_NAME_KEY

class InfoTaskFragment : Fragment() {

    private var _binding: FragmentInfoTaskBinding? = null
    private val binding get() = _binding!!
    private val task by lazy { arguments?.getSerializable(TASK_NAME_KEY) as Task? }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAppBar()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}