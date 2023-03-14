package com.quannv.searchapplication.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quannv.searchapplication.databinding.ItemDetailPhotoBinding
import com.quannv.searchapplication.response.Photo
import com.quannv.searchapplication.util.Const

class ImageDetailAdapter (private val data: MutableList<Photo>): RecyclerView.Adapter<ImageDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailPhotoBinding.inflate(
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

    fun removeItem(position: Int) {
        if (position in 0 until data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun addData(list: List<Photo>) {
        val positionStart = data.size
        data.addAll(list)
        notifyItemRangeInserted(positionStart, list.size)
    }

    fun canLoadMore(): Boolean = data.size % Const.PER_PAGE == 0

    class ViewHolder(private val binding: ItemDetailPhotoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: Photo) {
            binding.item = item
            binding.notifyChange()
        }
    }
}