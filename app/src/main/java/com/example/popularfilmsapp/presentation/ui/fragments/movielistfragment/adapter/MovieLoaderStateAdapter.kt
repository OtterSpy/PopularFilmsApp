package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.holders.ProgressViewHolder

class MovieLoaderStateAdapter : LoadStateAdapter<ProgressViewHolder>() {

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> Log.d("myLogs", "getStateViewType: ")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(
        holder: ProgressViewHolder,
        loadState: LoadState
    ) {
        /* no-op */
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ProgressViewHolder {
        return when (loadState) {
            LoadState.Loading -> ProgressViewHolder(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            is LoadState.Error -> error("Error")
            is LoadState.NotLoading -> error("Not supported")
        }
    }

    private companion object {
        private const val ERROR = 1
        private const val PROGRESS = 0
    }
}