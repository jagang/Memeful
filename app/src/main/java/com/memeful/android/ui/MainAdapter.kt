package com.memeful.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memeful.android.databinding.ItemMemeBinding
import com.memeful.android.model.GalleryData

class MainAdapter(private val items: ArrayList<GalleryData>) : RecyclerView.Adapter<MemeVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeVH {
        return MemeVH(ItemMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MemeVH, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}