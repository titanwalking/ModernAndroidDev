package com.alicankorkmaz.modernandroiddev.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alicankorkmaz.modernandroiddev.BR

class DataBindingListAdapter<I : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    diffUtil: DiffUtil.ItemCallback<I>,
    private val itemClickListener: (I) -> Unit
) : ListAdapter<I, ViewHolder<B>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        return ViewHolder<B>(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        ).apply {
            binding.root.setOnClickListener { itemClickListener.invoke(getItem(adapterPosition)) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

}

open class ViewHolder<B : ViewDataBinding>(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Any) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}