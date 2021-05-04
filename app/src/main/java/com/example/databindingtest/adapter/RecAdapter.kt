package com.example.databindingtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingtest.databinding.PixaItemBinding
import com.example.databindingtest.retrofit.model.Hit

class RecAdapter(val adapterClick: (String) -> Unit) :
    RecyclerView.Adapter<RecAdapter.HitsViewHolder>() {

    var data = listOf<Hit>()
    fun setList(list: List<Hit>) {
        this.data = list
        notifyDataSetChanged()
    }

    class HitsViewHolder(var binding: PixaItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PixaItemBinding.inflate(inflater, parent, false)
        return HitsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HitsViewHolder, position: Int) {
        holder.binding.hit = data[position]
        holder.binding.cardViewItem.setOnClickListener {
            adapterClick(data[position].largeImageURL)
        }
    }

}