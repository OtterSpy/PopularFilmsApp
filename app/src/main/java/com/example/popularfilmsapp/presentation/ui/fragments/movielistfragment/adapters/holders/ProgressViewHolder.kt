package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapters.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.popularfilmsapp.databinding.HolderProgressItemBinding
import com.example.popularfilmsapp.domain.model.MovieItem

class ProgressViewHolder(binding: HolderProgressItemBinding) :
    BaseViewHolder<HolderProgressItemBinding, MovieItem>(binding) {

    companion object {
        operator fun invoke(
            layoutInflater: LayoutInflater,
            parent: ViewGroup? = null,
            attachToRoot: Boolean = false
        ): ProgressViewHolder {
            return ProgressViewHolder(
                HolderProgressItemBinding.inflate(
                    layoutInflater,
                    parent,
                    attachToRoot
                )
            )
        }
    }

    override fun bind(item: MovieItem) {
        //Show Progress
    }
}