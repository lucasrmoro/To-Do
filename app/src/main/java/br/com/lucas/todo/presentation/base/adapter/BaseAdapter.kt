package br.com.lucas.todo.presentation.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<VB : ViewBinding, O : AdapterItem> :
    ListAdapter<O, BaseAdapter<VB, O>.GenericViewHolder>(DiffCallback<O>()) {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    fun setupList(adapterItemsList: List<O>) {
        submitList(adapterItemsList)
    }

    protected abstract fun adapterItemViewInflater(parent: ViewGroup, viewType: Int): VB

    protected abstract fun onBind(adapterItem: O)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        _binding = adapterItemViewInflater(parent, viewType)
        return GenericViewHolder()
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    inner class GenericViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(adapterItem: O) { onBind(adapterItem) }
    }

    private class DiffCallback<O : AdapterItem> : DiffUtil.ItemCallback<O>() {
        override fun areItemsTheSame(oldItem: O, newItem: O): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: O, newItem: O): Boolean {
            return oldItem == newItem
        }
    }
}