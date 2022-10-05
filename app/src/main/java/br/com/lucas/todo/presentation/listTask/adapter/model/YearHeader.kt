package br.com.lucas.todo.presentation.listTask.adapter.model

import br.com.lucas.todo.presentation.common.generic.adapter.model.AdapterItem
import br.com.lucas.todo.presentation.common.generic.adapter.model.AdapterItemType

data class YearHeader(
    val value: String?
) : AdapterItem {

    override fun itemViewType(): Int = AdapterItemType.YEAR_HEADER_ITEM

    override fun areItemsTheSame(toCompare: Any) =
        this.value == (toCompare as? YearHeader)?.value

    override fun areContentsTheSame(toCompare: Any) =
        this == toCompare

}