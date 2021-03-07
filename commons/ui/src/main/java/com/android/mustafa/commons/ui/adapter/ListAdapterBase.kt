package com.android.mustafa.commons.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class ListAdapterBase<T, V : ViewBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, ViewHolderBase<V>>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<V> {
        val binding = createBinding(parent)
        return ViewHolderBase(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onBindViewHolder(holder: ViewHolderBase<V>, position: Int) {
        bind(holder.binding, getItem(position))
    }

    protected abstract fun bind(binding: V, item: T)

}