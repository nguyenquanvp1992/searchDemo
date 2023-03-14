package com.quannv.searchapplication.util

import android.graphics.Bitmap
import androidx.collection.LruCache

object CacheBitmap {
    val cacheBitmap = LruCache<String, Bitmap>((Runtime.getRuntime().maxMemory() / 1024 / 8).toInt())
}