package br.com.lucas.todo.presentation.listTask.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.databinding.ListTaskMonthHeaderItemBinding
import br.com.lucas.todo.presentation.listTask.adapter.model.MonthHeader
import br.com.lucas.todo.presentation.listTask.adapter.model.YearHeader

class MonthHeaderViewHolder(private val binding: ListTaskMonthHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: MonthHeader) {
        binding.month.text = yearHeader.value
    }

}