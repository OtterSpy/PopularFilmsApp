package com.example.popularfilmsapp.presentation.ui.fragments

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularfilmsapp.databinding.HolderMovieItemBinding

class MovieListAdapter(
    private val activity: Activity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        with(holder) {

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    private class MovieViewHolder(val binding: HolderMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}