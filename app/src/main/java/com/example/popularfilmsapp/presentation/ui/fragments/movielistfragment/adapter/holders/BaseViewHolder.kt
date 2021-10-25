package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<V : ViewBinding, I>(val binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: I)
}