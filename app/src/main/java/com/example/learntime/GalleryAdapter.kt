package com.example.learntime

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.learntime.databinding.GalleryItemBinding

class GalleryAdapter(private val imageUris: List<Uri>) : RecyclerView.Adapter<ViewHolder>() {

    class GalleryViewHolder(val binding: GalleryItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = GalleryItemBinding.inflate(LayoutInflater.from(parent.context))
        return GalleryViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = (holder as? GalleryViewHolder)?.binding
        binding?.img?.setImageURI(imageUris[position])
    }

    override fun getItemCount() = imageUris.size
}