package br.com.lucas.todo.presentation.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<VB : ViewBinding, T> :
    ListAdapter<T, BaseAdapter<VB, T>.GenericViewHolder>(DiffCallback()) where T : DiffUtilEquality<T> {

    fun setupList(adapterItemsList: List<T>) {
        submitList(adapterItemsList)
    }

    protected abstract fun adapterItemViewInflater(parent: ViewGroup, viewType: Int): VB

    protected abstract fun onBind(binding: VB, adapterItem: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(adapterItemViewInflater(parent, viewType))
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = currentList.size

    inner class GenericViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adapterItem: T) {
            onBind(binding, adapterItem)
        }
    }

    private class DiffCallback<T : DiffUtilEquality<T>> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.areItemsTheSame(newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }
    }
}