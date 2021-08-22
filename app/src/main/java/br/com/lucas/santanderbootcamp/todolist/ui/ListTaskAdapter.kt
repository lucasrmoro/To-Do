package br.com.lucas.santanderbootcamp.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.santanderbootcamp.todolist.R
import br.com.lucas.santanderbootcamp.todolist.database.Task
import br.com.lucas.santanderbootcamp.todolist.databinding.ListTaskItemBinding

class ListTaskAdapter: RecyclerView.Adapter<ListTaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

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

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind(tasks[position])

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(private val binding: ListTaskItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(task: Task){
                binding.tvTitle.text = task.taskTitle
                binding.tvDate.text = "${task.taskDate} ${task.taskHour}"
            }

    }
}