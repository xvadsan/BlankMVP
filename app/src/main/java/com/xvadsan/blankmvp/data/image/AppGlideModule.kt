package com.xvadsan.blankmvp.data.image

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val diskCacheSizeBytes = 1024 * 1024 * 300 // 300 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, "cacheGlide", diskCacheSizeBytes.toLong()))
        builder.setDefaultRequestOptions { RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) }
        builder.setLogLevel(Log.DEBUG)

        val calculator = MemorySizeCalculator.Builder(context)
            .setBitmapPoolScreens(5f)
            .build()
        builder.setBitmapPool(LruBitmapPool(calculator.bitmapPoolSize.toLong()))
    }
}