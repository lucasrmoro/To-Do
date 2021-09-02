package br.com.lucas.santanderbootcamp.todolist.ui.listTask

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.core.extensions.OnItemClickListener
import br.com.lucas.santanderbootcamp.todolist.core.extensions.addOnItemClickListener
import br.com.lucas.santanderbootcamp.todolist.core.extensions.convertIntTimeToString
import br.com.lucas.santanderbootcamp.todolist.core.extensions.convertLongToDate
import br.com.lucas.santanderbootcamp.todolist.database.Task
import br.com.lucas.santanderbootcamp.todolist.databinding.ListTaskItemBinding
import br.com.lucas.santanderbootcamp.todolist.ui.infoTask.InfoTaskActivity

class ListTaskAdapter : ListAdapter<Task, ListTaskAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}

    fun addTask(tasks: List<Task>) {
        submitList(tasks)
    }

    fun listenerLaunchInfoTask(context: Context, listOfTasks: RecyclerView) {
        listOfTasks.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val task = getItem(position)
                InfoTaskActivity.launchInfoTaskActivity(context, task)
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
        fun bind(task: Task) {
            binding.tvTitle.text = task.taskTitle
            binding.tvDate.text =
                "${task.taskDate.convertLongToDate()} ${task.taskTime.convertIntTimeToString()}"
            binding.ivMore.setOnClickListener {
                showPopUp(task)
            }
        }

        private fun showPopUp(task: Task) {
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

    private class DiffCallback: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}