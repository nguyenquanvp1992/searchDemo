package com.quannv.searchapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.quannv.searchapplication.databinding.ItemImageBinding
import com.quannv.searchapplication.response.Photo

class ImageAdapter(private val data: MutableList<Photo>): Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in 0 until data.size) {
            holder.bindData(data[position])
        }
    }

    fun updateData(list: List<Photo>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Photo>) {
        val positionStart = data.size
        data.addAll(list)
        notifyItemRangeInserted(positionStart, list.size)
    }

    class ViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: Photo) {
            binding.item = item
            binding.notifyChange()
        }
    }
}