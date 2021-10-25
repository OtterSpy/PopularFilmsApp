package com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.popularfilmsapp.databinding.HolderActorsItemBinding
import com.example.popularfilmsapp.domain.model.Cast
import com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.adapter.holders.ActorViewHolder

class ActorsListAdapter : ListAdapter<Cast, RecyclerView.ViewHolder>(Companion) {

    private var onItemClickListener: ((Cast) -> Unit)? = null
    fun setOnClickListener(listener: (Cast) -> Unit) {
        onItemClickListener = listener
    }

    companion object : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorViewHolder(
            HolderActorsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ActorViewHolder -> {
                getItem(position).let { cast ->
                    holder.bind(cast)
                    holder.binding.actorsCardView.setOnClickListener {
                        onItemClickListener?.invoke(cast)
                    }
                }
            }
        }
    }
}