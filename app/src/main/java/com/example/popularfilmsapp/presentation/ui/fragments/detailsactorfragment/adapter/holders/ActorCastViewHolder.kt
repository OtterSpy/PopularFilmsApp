package com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment.adapter.holders

import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.ImageView
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.databinding.HolderActingBinding
import com.example.popularfilmsapp.domain.model.ActorMovieCast
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders.BaseViewHolder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ActorCastViewHolder(binding: HolderActingBinding) :
    BaseViewHolder<HolderActingBinding, ActorMovieCast>(binding) {
    override fun bind(item: ActorMovieCast) {
        if (item.posterPath != null) {
            Glide.with(itemView.context)
                .load(buildString {
                    append(Constants.BASE_IMAGE_URL)
                    append(item.posterPath)
                })
                .centerCrop()
                .into(binding.posterImageView)
        } else {
            binding.posterImageView.scaleType = ImageView.ScaleType.CENTER
            binding.posterImageView.setImageResource(R.drawable.ic_baseline_no_photography_48)
        }
        binding.movieTitleTextView.text = item.originalTitle
        if (item.releaseDate != null && item.releaseDate != "") {
            Log.d("TAG", "bind: ${item.releaseDate}")
            binding.releaseDateTextView.text = SpannableStringBuilder()
                .bold { append("Release date: ") }

                .append(item.releaseDate)
        } else {
            binding.releaseDateTextView.text = SpannableStringBuilder()
                .bold { append("Release date: ") }
                .append("вне пространства и времени")
        }
        if (item.character != null && item.character != "") {
            binding.characterTextView.text = SpannableStringBuilder()
                .bold { append("Character: ") }
                .append(item.character)
        } else {
            binding.characterTextView.text = SpannableStringBuilder()
                .bold { append("Character: ") }
                .append("какой-то гей наверное...")
        }
        binding.voteTextView.text = item.voteAverage
    }

}