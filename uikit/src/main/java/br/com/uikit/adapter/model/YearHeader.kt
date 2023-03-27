package br.com.uikit.adapter.model

data class YearHeader(
    val value: String?
) : AdapterItem {

    override fun itemViewType(): Int = AdapterItemType.YEAR_HEADER_ITEM

    override fun areItemsTheSame(toCompare: Any) =
        this.value == (toCompare as? YearHeader)?.value

    override fun areContentsTheSame(toCompare: Any) =
        this == toCompare

}