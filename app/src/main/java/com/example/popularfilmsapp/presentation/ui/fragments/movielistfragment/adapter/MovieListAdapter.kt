package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popularfilmsapp.databinding.HolderMovieItemBinding
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders.MovieViewHolder
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders.ProgressViewHolder

class MovieListAdapter(
) : PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(Companion) {

    private var onItemClickListener: ((MovieItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            HolderMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                getItem(position)?.let { movieItem ->
                    holder.bind(movieItem)
                    holder.binding.holderCardView.setOnClickListener {
                        onItemClickListener?.invoke(movieItem)
                    }
                }
            }
            is ProgressViewHolder -> {

            }
        }
    }
}