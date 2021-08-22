package br.com.lucas.santanderbootcamp.todolist.ui.listTask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.database.Task
import br.com.lucas.santanderbootcamp.todolist.databinding.ListTaskItemBinding

class ListTaskAdapter : RecyclerView.Adapter<ListTaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()
    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}

    fun addTask(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
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
        holder.bind(tasks[position])

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(private val binding: ListTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            binding.tvTitle.text = task.taskTitle
            binding.tvDate.text = "${task.taskDate} ${task.taskHour}"
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
}