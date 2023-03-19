package br.com.lucas.todo.presentation.adapter.viewHolders

import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.core.ext.isItalic
import br.com.lucas.todo.core.ext.setCheckedSilent
import br.com.lucas.todo.core.ext.showStrikeThrough
import br.com.lucas.todo.databinding.ListTaskItemBinding
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.adapter.callbacks.ListTaskAdapterCallback

class TaskItemViewHolder(
    private val binding: ListTaskItemBinding,
    private val listTaskAdapterCallback: ListTaskAdapterCallback?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(task: Task) {
        with (binding) {
            val checkboxListener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
                view?.run { listTaskAdapterCallback?.syncSelection(isChecked, task) }
            }

            root.setOnClickListener { listTaskAdapterCallback?.onTaskClicked(task) }
            tvTitle.text = task.title
            tvTitle.isSelected = task.isSelected
            tvTitle.showStrikeThrough(task.isSelected)
            tvTitle.isItalic(task.isSelected)
            tvHour.text = task.hour
            tvHour.isSelected = task.isSelected
            tvMinute.text = task.minute
            tvMinute.isSelected = task.isSelected
            checkbox.setCheckedSilent(task.isSelected, checkboxListener)
            checkbox.setOnCheckedChangeListener(checkboxListener)
        }
    }

}