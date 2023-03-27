package br.com.uikit.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.uikit.adapter.viewholder.DayHeaderViewHolder
import br.com.uikit.adapter.viewholder.MonthHeaderViewHolder
import br.com.uikit.adapter.viewholder.TaskItemViewHolder
import br.com.uikit.adapter.viewholder.YearHeaderViewHolder
import br.com.uikit.adapter.callbacks.ListTaskAdapterCallback
import br.com.uikit.adapter.model.*
import br.com.uikit.databinding.ListTaskDayHeaderItemBinding
import br.com.uikit.databinding.ListTaskItemBinding
import br.com.uikit.databinding.ListTaskMonthHeaderItemBinding
import br.com.uikit.databinding.ListTaskYearHeaderItemBinding
import br.com.uikit.extensions.getLayoutInflater

class GenericAdapter(
    private val adapterCallbacks: GenericAdapterCallback? = null
) : ListAdapter<AdapterItem, RecyclerView.ViewHolder>(GenericItemDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val layoutInflater = parent.getLayoutInflater()
        return when (viewType) {
            AdapterItemType.TASK_ITEM -> TaskItemViewHolder(
                ListTaskItemBinding.inflate(
                    layoutInflater, parent, false
                ),
                adapterCallbacks as? ListTaskAdapterCallback
            )
            AdapterItemType.YEAR_HEADER_ITEM -> YearHeaderViewHolder(
                ListTaskYearHeaderItemBinding.inflate(
                    layoutInflater, parent, false
                )
            )
            AdapterItemType.MONTH_HEADER_ITEM -> MonthHeaderViewHolder(
                ListTaskMonthHeaderItemBinding.inflate(layoutInflater, parent, false)
            )
            AdapterItemType.DAY_HEADER_ITEM -> DayHeaderViewHolder(
                ListTaskDayHeaderItemBinding.inflate(
                    layoutInflater, parent, false
                )
            )
            else -> throw IllegalArgumentException("AdapterItemType Invalid")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is TaskItemViewHolder -> {
                holder.onBind(getItem(position) as Task)
            }
            is YearHeaderViewHolder -> {
                holder.onBind(getItem(position) as YearHeader)
            }
            is MonthHeaderViewHolder -> {
                holder.onBind(getItem(position) as MonthHeader)
            }
            is DayHeaderViewHolder -> {
                holder.onBind(getItem(position) as DayHeader)
            }
        }
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].itemViewType()
}