package br.com.lucas.todo.presentation.listTask

import android.view.View
import android.view.ViewGroup
import br.com.lucas.todo.R
import br.com.lucas.todo.core.ext.getLayoutInflater
import br.com.lucas.todo.core.ext.showPopUp
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
        binding.root.setOnClickListener { listTaskAdapterInterface.onTaskClicked(adapterItem) }
        binding.tvTitle.text = adapterItem.taskTitle
        binding.tvDate.text = adapterItem.taskDate
        binding.tvTime.text = adapterItem.taskTime
        binding.ivMore.setOnClickListener { showPopUp(it, adapterItem) }
    }

    private fun showPopUp(view: View, task: Task) = with(view) {
        showPopUp { itemId ->
            when (itemId) {
                R.id.popup_menu_edit_action -> listTaskAdapterInterface.onEditOptionClicked(task)
                R.id.popup_menu_delete_action -> listTaskAdapterInterface.onDeleteOptionClicked(task)
            }
        }
    }
}