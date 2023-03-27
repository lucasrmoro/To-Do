package br.com.uikit.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.uikit.adapter.model.MonthHeader
import br.com.uikit.databinding.ListTaskMonthHeaderItemBinding

class MonthHeaderViewHolder(private val binding: ListTaskMonthHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: MonthHeader) {
        binding.month.text = yearHeader.value
    }

}