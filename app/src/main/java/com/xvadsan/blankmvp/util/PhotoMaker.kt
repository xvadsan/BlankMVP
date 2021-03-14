package com.xvadsan.blankmvp.util

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts

class PhotoMaker(
    activityResultRegistry: ActivityResultRegistry,
    callback: (boolean: Boolean?) -> Unit
) {

    private val getPhoto: ActivityResultLauncher<Uri> =
        activityResultRegistry.register(REGISTRY_KEY, ActivityResultContracts.TakePicture(), callback)

    fun takePhoto(uri: Uri?) {
        getPhoto.launch(uri)
    }

    companion object {
        private const val REGISTRY_KEY = "photoKey"
    }
}
