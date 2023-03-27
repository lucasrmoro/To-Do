package br.com.uikit.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.uikit.adapter.model.DayHeader
import br.com.uikit.databinding.ListTaskDayHeaderItemBinding

class DayHeaderViewHolder(private val binding: ListTaskDayHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(yearHeader: DayHeader) {
        binding.day.text = yearHeader.value
    }

}