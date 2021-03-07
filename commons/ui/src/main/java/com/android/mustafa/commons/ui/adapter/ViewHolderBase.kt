package com.android.mustafa.commons.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ViewHolderBase<out T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)