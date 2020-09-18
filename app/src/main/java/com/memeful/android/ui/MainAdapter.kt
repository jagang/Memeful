package com.memeful.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memeful.android.databinding.ItemMemeBinding
import com.memeful.android.model.GalleryData

class MainAdapter(private val items: ArrayList<GalleryData>, private val onBottomReachedListener: OnBottomReachedListener) : RecyclerView.Adapter<MemeVH>() {

    private val threshold = 60

    interface OnBottomReachedListener {
        fun onBottomReached(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeVH {
        return MemeVH(ItemMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MemeVH, position: Int) {
        holder.bind(items[holder.adapterPosition])

        if (position == (itemCount - threshold) || holder.adapterPosition == (itemCount - 2)|| holder.adapterPosition == (itemCount - 1)) {
            onBottomReachedListener.onBottomReached(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(listOfGalleryData: List<GalleryData>) {
        items.addAll(listOfGalleryData)
        notifyItemRangeInserted(items.size - listOfGalleryData.size, items.size)
    }
}