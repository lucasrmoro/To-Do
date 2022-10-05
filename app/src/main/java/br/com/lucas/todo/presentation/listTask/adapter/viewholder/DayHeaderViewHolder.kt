package br.com.lucas.todo.presentation.listTask.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.databinding.ListTaskDayHeaderItemBinding
import br.com.lucas.todo.databinding.ListTaskYearHeaderItemBinding
import br.com.lucas.todo.presentation.listTask.adapter.model.DayHeader
import br.com.lucas.todo.presentation.listTask.adapter.model.YearHeader

class DayHeaderViewHolder(private val binding: ListTaskDayHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: DayHeader) {
        binding.day.text = yearHeader.value
    }

}