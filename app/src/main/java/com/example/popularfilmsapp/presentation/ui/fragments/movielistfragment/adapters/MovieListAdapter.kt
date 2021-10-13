package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popularfilmsapp.databinding.HolderMovieItemBinding
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapters.holders.MovieViewHolder
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapters.holders.ProgressViewHolder

class MovieListAdapter(
    private val activity: Activity
) : PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(Companion) {

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
                getItem(position)?.let { product ->
                    holder.bind(product)
                }
            }
            is ProgressViewHolder -> {

            }
        }
    }

}