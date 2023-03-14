package com.quannv.searchapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.quannv.searchapplication.databinding.ItemImageBinding
import com.quannv.searchapplication.response.Photo
import com.quannv.searchapplication.util.Const

class ImageAdapter(private val data: MutableList<Photo>, private val onItemClick: () -> Unit): Adapter<ImageAdapter.ViewHolder>() {

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
            holder.itemView.setOnClickListener {
                onItemClick.invoke()
            }
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

    fun canLoadMore(): Boolean = data.size % Const.PER_PAGE == 0

    class ViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: Photo) {
            binding.item = item
            binding.notifyChange()
        }
    }
}