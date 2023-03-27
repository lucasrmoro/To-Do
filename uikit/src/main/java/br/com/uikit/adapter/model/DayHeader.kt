package br.com.uikit.adapter.model

data class DayHeader(
    val value: String?
) : AdapterItem {

    override fun itemViewType(): Int = AdapterItemType.DAY_HEADER_ITEM

    override fun areItemsTheSame(toCompare: Any) =
        this.value == (toCompare as? DayHeader)?.value

    override fun areContentsTheSame(toCompare: Any) =
        this == toCompare
}