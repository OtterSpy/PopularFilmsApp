package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders

import android.view.View
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.databinding.HolderMovieItemBinding
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.MovieListAdapter
import java.util.*

class MovieViewHolder(binding: HolderMovieItemBinding) :
    BaseViewHolder<HolderMovieItemBinding, MovieItem>(binding) {

    override fun bind(item: MovieItem) {
        binding.voteAverageTextView.text = item.voteAverage
        binding.holderTitleTextView.text = item.title
        Glide.with(itemView.context)
            .load(buildString {
                append(Constants.BASE_IMAGE_URL)
                append(item.posterPath)
            })
            .centerCrop()
            .into(binding.holderImageView)
    }
}