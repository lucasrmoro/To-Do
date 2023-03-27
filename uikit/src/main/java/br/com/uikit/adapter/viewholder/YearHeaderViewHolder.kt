package br.com.uikit.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.uikit.adapter.model.YearHeader
import br.com.uikit.databinding.ListTaskYearHeaderItemBinding

class YearHeaderViewHolder(private val binding: ListTaskYearHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: YearHeader) {
        binding.year.text = yearHeader.value
    }

}