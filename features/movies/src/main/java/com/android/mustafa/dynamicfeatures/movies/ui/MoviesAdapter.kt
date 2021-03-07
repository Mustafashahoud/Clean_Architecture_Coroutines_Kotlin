package com.android.mustafa.dynamicfeatures.movies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.android.mustafa.commons.ui.adapter.ListAdapterBase
import com.android.mustafa.commons.ui.bindings.load
import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.dynamicfeatures.movies.databinding.ItemMovieBinding
import com.android.mustafa.dynamicfeatures.movies.util.ApiUrlsUtil

class MoviesAdapter : ListAdapterBase<Movie, ItemMovieBinding>(object :
    DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {
    override fun createBinding(parent: ViewGroup): ItemMovieBinding {
        return ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
    }

    override fun bind(binding: ItemMovieBinding, item: Movie) {
        binding.imageView.load(item.poster_path?.let { ApiUrlsUtil.getPosterPath(it) })
        binding.textView.text = item.title
    }
}