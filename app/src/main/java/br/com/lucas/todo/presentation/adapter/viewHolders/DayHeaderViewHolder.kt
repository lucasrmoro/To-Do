package br.com.lucas.todo.presentation.adapter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.databinding.ListTaskDayHeaderItemBinding
import br.com.lucas.todo.domain.model.DayHeader

class DayHeaderViewHolder(private val binding: ListTaskDayHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: DayHeader) {
        binding.day.text = yearHeader.value
    }

}