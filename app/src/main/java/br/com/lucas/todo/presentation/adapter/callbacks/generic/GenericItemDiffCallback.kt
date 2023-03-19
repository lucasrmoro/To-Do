package br.com.lucas.todo.presentation.adapter.callbacks.generic

import androidx.recyclerview.widget.DiffUtil
import br.com.lucas.todo.domain.model.adapter.AdapterItem

class GenericItemDiffCallback : DiffUtil.ItemCallback<AdapterItem>() {
    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}