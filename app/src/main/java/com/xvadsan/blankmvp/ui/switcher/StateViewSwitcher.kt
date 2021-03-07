package com.xvadsan.blankmvp.ui.switcher

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

interface StateViewSwitcher {
    fun init(view: View)
    fun switchToMain()
    fun switchToError(
        errorMessage: String?,
        throwable: Throwable?,
        errorListener: () -> Unit
    )
    fun showError(message: String?)
    fun switchToLoading(type: LoadingType)
    fun switchToLoading()
    fun switchToView(@LayoutRes idLayout: Int, @IdRes idControlView: Int?, clickListener: () -> Unit)
    fun destroy()
}