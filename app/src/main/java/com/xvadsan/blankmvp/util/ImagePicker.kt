package com.xvadsan.blankmvp.util

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts

class ImagePicker(
    activityResultRegistry: ActivityResultRegistry,
    callback: (image: Uri?) -> Unit
) {

    private val getContent: ActivityResultLauncher<String> =
        activityResultRegistry.register(REGISTRY_KEY, ActivityResultContracts.GetContent(), callback)

    fun pickImage() {
        getContent.launch(MIME_TYPE)
    }

    companion object {
        private const val REGISTRY_KEY = "registryKey"
        private const val MIME_TYPE = "image/*"
    }
}