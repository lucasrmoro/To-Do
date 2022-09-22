package br.com.lucas.todo.presentation.listTask

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.R
import br.com.lucas.todo.core.Constants.TASK_TO_EDIT
import br.com.lucas.todo.core.ext.OnItemClickListener
import br.com.lucas.todo.core.ext.addOnItemClickListener
import br.com.lucas.todo.core.ext.convertIntTimeToString
import br.com.lucas.todo.core.ext.convertLongToFullDate
import br.com.lucas.todo.data.db.entities.TaskEntity
import br.com.lucas.todo.databinding.ListTaskItemBinding

class ListTaskAdapter : ListAdapter<TaskEntity, ListTaskAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerEdit: (TaskEntity) -> Unit = {}
    var listenerDelete: (TaskEntity) -> Unit = {}

    fun addTask(tasks: List<TaskEntity>) {
        submitList(tasks)
    }

    fun listenerLaunchInfoTask(context: Context, listOfTasks: RecyclerView) {
        listOfTasks.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val task = Bundle().apply { putParcelable(TASK_TO_EDIT, getItem(position)) }
                view.findNavController().navigate(R.id.fromListTaskToInfoTask, task)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ListTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class TaskViewHolder(private val binding: ListTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(task: TaskEntity) {
            binding.tvTitle.text = task.taskTitle
            binding.tvDate.text = task.taskDate.convertLongToFullDate()
            binding.tvTime.text = task.taskTime.convertIntTimeToString()
            binding.ivMore.setOnClickListener {
                showPopUp(task)
            }
        }

        private fun showPopUp(task: TaskEntity) {
            val ivMore = binding.ivMore
            val popUpMenu = PopupMenu(ivMore.context, ivMore)
            popUpMenu.menuInflater.inflate(R.menu.popup_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.popup_menu_edit_action -> listenerEdit(task)
                    R.id.popup_menu_delete_action -> listenerDelete(task)
                }
                true
            }
            popUpMenu.show()
        }

    }

    private class DiffCallback: DiffUtil.ItemCallback<TaskEntity>(){
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem == newItem
        }
    }
}