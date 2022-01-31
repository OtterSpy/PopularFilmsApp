package com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.adapter.holders

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.databinding.HolderActorsItemBinding
import com.example.popularfilmsapp.domain.model.Cast
import com.example.popularfilmsapp.utils.BaseViewHolder

class ActorViewHolder(binding: HolderActorsItemBinding) :
    BaseViewHolder<HolderActorsItemBinding, Cast>(binding) {
    override fun bind(item: Cast) {
        if (!item.profilePath.contentEquals(null)) {
            Glide.with(itemView.context)
                .load(buildString {
                    append(Constants.BASE_IMAGE_URL)
                    append(item.profilePath)
                })
                .into(binding.actorsImageView)
        } else {
            binding.actorsImageView.scaleType = ImageView.ScaleType.CENTER
            binding.actorsImageView.setImageResource(R.drawable.ic_actor_place_holder_image)
        }

        binding.actorsTextView.text = item.name
    }
}