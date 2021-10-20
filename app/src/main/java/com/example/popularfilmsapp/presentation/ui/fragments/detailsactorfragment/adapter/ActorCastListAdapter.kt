package com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.popularfilmsapp.databinding.HolderActingBinding
import com.example.popularfilmsapp.domain.model.ActorMovieCast
import com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment.adapter.holders.ActorCastViewHolder

class ActorCastListAdapter: ListAdapter<ActorMovieCast, RecyclerView.ViewHolder>(Companion) {

    private var onItemClickListener: ((ActorMovieCast) -> Unit)? = null
    fun setOnClickListener(listener: (ActorMovieCast) -> Unit) {
        onItemClickListener = listener
    }

    companion object : DiffUtil.ItemCallback<ActorMovieCast>() {
        override fun areItemsTheSame(oldItem: ActorMovieCast, newItem: ActorMovieCast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ActorMovieCast, newItem: ActorMovieCast): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorCastViewHolder(
            HolderActingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ActorCastViewHolder -> {
                getItem(position).let { movieCast ->
                    holder.bind(movieCast)
                    holder.binding.actingContainerCardView.setOnClickListener {
                        onItemClickListener?.invoke(movieCast)
                    }
                }
            }
        }
    }
}