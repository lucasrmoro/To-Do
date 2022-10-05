package br.com.lucas.todo.presentation.common.generic.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.lucas.todo.presentation.common.generic.adapter.model.AdapterItem

class GenericItemDiffCallback : DiffUtil.ItemCallback<AdapterItem>() {
    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}