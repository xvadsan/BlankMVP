package com.xvadsan.blankmvp.base.extensions

class PermissionBuilder {

    var granted: (permission: String) -> Unit = {}

    var denied: (permission: String) -> Unit = {}

    fun granted(callback: (permission: String) -> Unit) {
        this.granted = callback
    }

    fun denied(callback: (permission: String) -> Unit) {
        this.denied = callback
    }
}