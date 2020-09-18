package com.memeful.android.ui

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.memeful.android.databinding.ItemMemeBinding
import com.memeful.android.model.GalleryData

class MemeVH(private val binding: ItemMemeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(galleryData: GalleryData) {
        galleryData.images?.get(0)?.let { imageData ->
            imageData.type?.let {
                if (it.contains("image")) {
                    binding.ivMeme.load(imageData.link)
                }
            }
        }
    }

}
