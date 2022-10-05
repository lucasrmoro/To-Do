package br.com.lucas.todo.presentation.listTask.adapter.viewholder

import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.core.ext.isItalic
import br.com.lucas.todo.core.ext.setCheckedSilent
import br.com.lucas.todo.core.ext.showStrikeThrough
import br.com.lucas.todo.databinding.ListTaskItemBinding
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.listTask.adapter.ListTaskAdapterCallback

class TaskItemViewHolder(
    private val binding: ListTaskItemBinding,
    private val listTaskAdapterCallback: ListTaskAdapterCallback?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(task: Task) {
        val checkboxListener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
            view?.run { listTaskAdapterCallback?.syncSelection(isChecked, task) }
        }

        binding.root.setOnClickListener { listTaskAdapterCallback?.onTaskClicked(task) }
        binding.tvTitle.text = task.title
        binding.tvTitle.isSelected = task.isSelected
        binding.tvTitle.showStrikeThrough(task.isSelected)
        binding.tvTitle.isItalic(task.isSelected)
        binding.tvHour.text = task.hour
        binding.tvHour.isSelected = task.isSelected
        binding.tvMinute.text = task.minute
        binding.tvMinute.isSelected = task.isSelected
        binding.checkbox.setCheckedSilent(task.isSelected, checkboxListener)
        binding.checkbox.setOnCheckedChangeListener(checkboxListener)
    }

}




