package br.com.lucas.todo.presentation.listTask

import android.view.ViewGroup
import android.widget.CompoundButton
import br.com.lucas.todo.core.ext.getLayoutInflater
import br.com.lucas.todo.core.ext.isItalic
import br.com.lucas.todo.core.ext.setCheckedSilent
import br.com.lucas.todo.core.ext.showStrikeThrough
import br.com.lucas.todo.databinding.ListTaskItemBinding
import br.com.lucas.todo.domain.model.Task
import br.com.lucas.todo.presentation.base.adapter.BaseAdapter

class ListTaskAdapter(
    private val listTaskAdapterInterface: ListTaskAdapterInterface
) : BaseAdapter<ListTaskItemBinding, Task>() {

    override fun adapterItemViewInflater(
        parent: ViewGroup,
        viewType: Int
    ) = ListTaskItemBinding.inflate(parent.getLayoutInflater(), parent, false)

    override fun onBind(binding: ListTaskItemBinding, adapterItem: Task) {
        val checkboxListener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
            view?.run { listTaskAdapterInterface.syncSelection(isChecked, adapterItem) }
        }

        binding.root.setOnClickListener { listTaskAdapterInterface.onTaskClicked(adapterItem) }
        binding.tvTitle.text = adapterItem.taskTitle
        binding.tvTitle.isSelected = adapterItem.isSelected
        binding.tvTitle.showStrikeThrough(adapterItem.isSelected)
        binding.tvTitle.isItalic(adapterItem.isSelected)
        binding.tvHour.text = adapterItem.taskHour
        binding.tvHour.isSelected = adapterItem.isSelected
        binding.tvMinute.text = adapterItem.taskMinute
        binding.tvMinute.isSelected = adapterItem.isSelected
        binding.checkbox.setCheckedSilent(adapterItem.isSelected, checkboxListener)
        binding.checkbox.setOnCheckedChangeListener(checkboxListener)
    }

}