package com.xvadsan.blankmvp.base.extensions

class MultiplePermissionsBuilder {

    var allGranted: () -> Unit = {}

    var denied: (List<String>) -> Unit = {}

    fun allGranted(callback: () -> Unit) {
        this.allGranted = callback
    }

    fun denied(callback: (List<String>) -> Unit) {
        this.denied = callback
    }
}