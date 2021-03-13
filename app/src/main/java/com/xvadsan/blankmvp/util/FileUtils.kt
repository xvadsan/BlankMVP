package com.xvadsan.blankmvp.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.IOException

class FileUtils {

    fun getBitmapFromUri(context: Context, uri: Uri?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        } catch (e: IOException) {
            Log.d("TAG", "getBitmapFromUri: ${e.message.toString()}")
        }
        return bitmap
    }

    fun getImageByteArray(bmp: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
        return outputStream.toByteArray()
    }
}