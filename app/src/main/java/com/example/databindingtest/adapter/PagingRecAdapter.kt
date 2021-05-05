package com.example.databindingtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingtest.R
import com.example.databindingtest.databinding.PixaItemBinding
import com.example.databindingtest.retrofit.model.Hit

class PagingRecAdapter(val adapterClick: (String) -> Unit) : PagingDataAdapter<Hit, PagingRecAdapter.PagingViewHolder>(
    PHOTO_COMPORATOR
) {

    inner class PagingViewHolder(private val binding: PixaItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hit: Hit) {
            binding.hit = hit
            binding.cardViewItem.setOnClickListener {
                adapterClick(hit.largeImageURL)
            }
        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = PixaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingViewHolder(binding)
    }

    companion object {
        private val PHOTO_COMPORATOR = object : DiffUtil.ItemCallback<Hit>() {
            override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
                return oldItem == newItem
            }
        }
    }
}