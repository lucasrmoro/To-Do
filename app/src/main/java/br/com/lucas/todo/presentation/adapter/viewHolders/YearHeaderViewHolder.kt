package br.com.lucas.todo.presentation.adapter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.todo.databinding.ListTaskYearHeaderItemBinding
import br.com.lucas.todo.domain.model.YearHeader

class YearHeaderViewHolder(private val binding: ListTaskYearHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: YearHeader) {
        binding.year.text = yearHeader.value
    }

}