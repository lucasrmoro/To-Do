package br.com.lucas.todo.domain.model

import br.com.lucas.todo.domain.model.adapter.AdapterItem
import br.com.lucas.todo.domain.model.adapter.AdapterItemType

data class MonthHeader(
    val value: String?
) : AdapterItem {

    override fun itemViewType(): Int = AdapterItemType.MONTH_HEADER_ITEM

    override fun areItemsTheSame(toCompare: Any) =
        this.value == (toCompare as? MonthHeader)?.value

    override fun areContentsTheSame(toCompare: Any) =
        this == toCompare

}