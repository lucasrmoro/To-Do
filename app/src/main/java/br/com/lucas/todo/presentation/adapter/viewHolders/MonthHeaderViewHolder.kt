package br.com.lucas.todo.presentation.adapter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.databinding.ListTaskMonthHeaderItemBinding
import br.com.lucas.todo.domain.model.MonthHeader

class MonthHeaderViewHolder(private val binding: ListTaskMonthHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: MonthHeader) {
        binding.month.text = yearHeader.value
    }

}