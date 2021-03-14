package com.xvadsan.blankmvp.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.xvadsan.blankmvp.base.extensions.getDateTimeOfPattern
import org.threeten.bp.LocalDateTime
import java.io.ByteArrayOutputStream
import java.io.File
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

    @Throws(IOException::class)
    fun createPhoto(ctx: Context): File = File.createTempFile(
        LocalDateTime.now().getDateTimeOfPattern("dd_LL_YYYY_HH_mm_SS"), ".jpg", ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    )
}