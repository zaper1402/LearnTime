package com.example.learntime.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learntime.databinding.MainGridItemBinding
import com.example.learntime.pojos.MainGridPojo


class MainGridAdapter(private val list : MutableList<MainGridPojo>,private val delegateClickListener: DelegateClickListener) : RecyclerView.Adapter<MainGridAdapter.MainGridViewHolder>() {


    inner class MainGridViewHolder(val binding: MainGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: MainGridPojo) {
            binding.title.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainGridViewHolder {
        val view: MainGridItemBinding = MainGridItemBinding.inflate(LayoutInflater.from(parent.context))
        return MainGridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainGridViewHolder, position: Int) {
        holder.bindView(list[position])
        holder.binding.root.setOnClickListener {
            delegateClickListener.onClick(null,list[position].id,null)
        }
    }

    fun updateRecyclerView(gridList : MutableList<MainGridPojo>) {
        list.clear()
        list.addAll(gridList)
    }
}