package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.databinding.HolderMovieItemBinding
import com.example.popularfilmsapp.domain.model.MovieItem
import kotlin.math.roundToInt

class MovieListAdapter(
    private val activity: Activity
) : ListAdapter<MovieItem, RecyclerView.ViewHolder>(Companion) {

    private val movieItems = mutableListOf<MovieItem>()

    companion object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    fun mySubmitList(list: List<MovieItem>) {
        movieItems.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    private class MovieViewHolder(val binding: HolderMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        with(holder as MovieViewHolder) {
            movieItems[position].let { product ->
                binding.holderTitleTextView.text = product.title
                Glide.with(holder.itemView.context)
                    .load(buildString {
                        append(Constants.BASE_IMAGE_URL)
                        append(product.posterPath)
                    })
                    .centerCrop()
                    .into(binding.holderImageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieItems.size
    }
}