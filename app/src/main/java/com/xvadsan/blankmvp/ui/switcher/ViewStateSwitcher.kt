package com.xvadsan.blankmvp.ui.switcher

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.extensions.hide
import com.xvadsan.blankmvp.base.extensions.onClick
import com.xvadsan.blankmvp.base.extensions.show
import com.xvadsan.blankmvp.ui.switcher.StateView.*
import kotlinx.android.synthetic.main.state_error_screen.view.*
import javax.inject.Inject


open class ViewStateSwitcher @Inject constructor() : StateViewSwitcher {

    protected lateinit var layoutInflater: LayoutInflater
    protected lateinit var container: ViewGroup
    protected val states = HashMap<StateView, ViewInfo>()
    protected var mainView: View? = null

    protected class ViewInfo(var layoutId: Int, var view: View?)

    override fun init(view: View) {
        mainView = view
        states[STATE_MAIN] = ViewInfo(0, mainView)
        layoutInflater = LayoutInflater.from(mainView?.context)
        container = mainView?.parent as ViewGroup
    }

    override fun switchToMain() = switchToState(STATE_MAIN)

    override fun switchToView(
        @LayoutRes idLayout: Int,
        @IdRes idControlView: Int?,
        clickListener: () -> Unit
    ) {
        val loadingView = layoutInflater.inflate(idLayout, container, false)
        if (idControlView != null) loadingView.findViewById<View>(idControlView).onClick { clickListener.invoke() }
        addViewState(STATE_NEW_VIEW, loadingView)
        switchToState(STATE_NEW_VIEW)
        show(loadingView)
    }

    override fun switchToLoading(type: LoadingType) {
        val loadingView = when (type) {
            LoadingType.PROGRESS_BAR -> layoutInflater.inflate(
                R.layout.state_loading_screen,
                container,
                false
            )
        }
        addViewState(STATE_LOADING, loadingView)
        switchToState(STATE_LOADING)
        show(loadingView)
    }

    override fun switchToLoading() = switchToLoading(LoadingType.PROGRESS_BAR)

    override fun switchToError(
        errorMessage: String?,
        throwable: Throwable?,
        errorListener: () -> Unit
    ) {
        val errorView = layoutInflater.inflate(R.layout.state_error_screen, container, false)
        errorView.tvError.text =
            errorMessage ?: mainView?.context?.getString(R.string.state_unknown_error)
        errorView.tvOkBtn.onClick {
            errorListener.invoke()
        }
        addViewState(STATE_ERROR, errorView)
        switchToError()
        if (throwable != null) buildStackTrace(errorView, throwable)
    }


    override fun showError(message: String?) {
        mainView?.let {
            val errorMessage: String = message ?: it.context.getString(R.string.state_unknown_error)
            Toast.makeText(it.context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun switchToState(state: StateView) {
        val viewInfo = states[state] ?: return
        if (viewInfo.view == null) {
            viewInfo.view = layoutInflater.inflate(viewInfo.layoutId, container, false)
        }
        val nextView = viewInfo.view
        if (nextView == null || nextView == mainView) return
        if (nextView.parent != container) container.addView(nextView)
        show(nextView)
    }

    protected fun addViewState(state: StateView, stateView: View) {
        stateView.hide()
        states[state] = ViewInfo(0, stateView)
    }

    protected fun switchToError() = switchToState(STATE_ERROR)

    protected fun show(nextView: View) {
        mainView?.hide()
        nextView.show()
        mainView = nextView
    }

    private fun buildStackTrace(error: View, throwable: Throwable) =
        error.findViewById<View>(R.id.tvError)?.onClick {
            error.findViewById<TextView>(R.id.tvError)?.apply {
                text = if (throwable.cause == null) {
                    throwable.message
                } else {
                    throwable.message + "\n" + throwable.cause?.message
                }
                onClick {
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                    val text = StringBuilder()
                        .appendLine("Error message:")
                        .appendLine(throwable.message)
                        .appendLine("Error stack:")
                        .appendLine(Log.getStackTraceString(throwable))
                        .apply {
                            if (throwable.cause != null) {
                                appendLine("\nCause Error:")
                                appendLine("Error message:")
                                appendLine(throwable.cause?.message)
                                appendLine("Error stack:")
                                appendLine(Log.getStackTraceString(throwable.cause))
                            }
                        }
                    val clip = ClipData.newPlainText("Error Stack", text)
                    clipboard?.setPrimaryClip(clip)
                    Toast.makeText(context, "Error info copied!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    override fun destroy() {
        mainView = null
    }
}

