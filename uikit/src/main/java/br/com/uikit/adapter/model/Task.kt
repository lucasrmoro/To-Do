package br.com.uikit.adapter.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Task(
    val uuid: UUID? = null,
    val title: String,
    val description: String,
    val date: String,
    val hour: String,
    val minute: String,
    val isSelected: Boolean = false
) : Parcelable, AdapterItem {

    override fun itemViewType() = AdapterItemType.TASK_ITEM

    override fun areItemsTheSame(toCompare: Any) =
        this.uuid == (toCompare as? Task)?.uuid

    override fun areContentsTheSame(toCompare: Any) =
        this == toCompare

}