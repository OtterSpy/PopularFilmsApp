package com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.adapter.holders

import com.bumptech.glide.Glide
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.databinding.HolderActorsItemBinding
import com.example.popularfilmsapp.domain.model.Cast
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders.BaseViewHolder

class ActorViewHolder(binding: HolderActorsItemBinding) :
    BaseViewHolder<HolderActorsItemBinding, Cast>(binding) {
    override fun bind(item: Cast) {
        Glide.with(itemView.context)
            .load(buildString {
                append(Constants.BASE_IMAGE_URL)
                append(item.profilePath)
            })
            .into(binding.actorsImageView)
        binding.actorsTextView.text = item.name
    }
}