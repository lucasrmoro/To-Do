package br.com.lucas.todo.presentation.listTask.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.databinding.ListTaskYearHeaderItemBinding
import br.com.lucas.todo.presentation.listTask.adapter.model.YearHeader

class YearHeaderViewHolder(private val binding: ListTaskYearHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: YearHeader) {
        binding.year.text = yearHeader.value
    }

}