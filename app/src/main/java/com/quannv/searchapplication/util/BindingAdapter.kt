package com.quannv.searchapplication.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    GlobalScope.launch(Dispatchers.Main) {
        loadImageFromUrl(url)
    }
}

suspend fun ImageView.loadImageFromUrl(url: String) {
    val bitmap = CacheBitmap.cacheBitmap[url] ?: getBitmapFromUrl(url)
    CacheBitmap.cacheBitmap.put(url, bitmap)
    this.setImageBitmap(bitmap)
}

suspend fun getBitmapFromUrl(url: String): Bitmap {
    return withContext(Dispatchers.IO) {
        val urlConnection = URL(url)
        BitmapFactory.decodeStream(urlConnection.openConnection().getInputStream())
    }
}