package br.com.lucas.todo.presentation.listTask.adapter.model

import br.com.lucas.todo.presentation.common.generic.adapter.model.AdapterItem
import br.com.lucas.todo.presentation.common.generic.adapter.model.AdapterItemType

data class DayHeader(
    val value: String?
) : AdapterItem {

    override fun itemViewType(): Int = AdapterItemType.DAY_HEADER_ITEM

    override fun areItemsTheSame(toCompare: Any) =
        this.value == (toCompare as? DayHeader)?.value

    override fun areContentsTheSame(toCompare: Any) =
        this == toCompare
}