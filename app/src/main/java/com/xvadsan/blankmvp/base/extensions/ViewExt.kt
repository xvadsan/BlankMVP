@file:Suppress("NOTHING_TO_INLINE")

package com.xvadsan.blankmvp.base.extensions

import android.content.Context
import android.graphics.Color
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SearchView
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.Group
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


inline fun View.show() {
    visibility = View.VISIBLE
}

inline fun View.invisible() {
    visibility = View.INVISIBLE
}

inline fun View.hide() {
    visibility = View.GONE
}

inline fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

inline fun View.isHidden(): Boolean = visibility == View.GONE

fun TextInputEditText?.showKeyboard() {
    this ?: return
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        ?: return
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.moveToNextEditTextAfterClickEnter(nextET: EditText) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
            nextET.requestFocus()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

fun EditText?.showKeyboard() {
    this ?: return
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        ?: return
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun SearchView?.showKeyboard() {
    this ?: return
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

inline fun TextInputEditText.afterTextChanged(crossinline afterTextChanged: (string: Editable) -> Unit): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) = afterTextChanged(s)

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit
    }
    addTextChangedListener(textWatcher)
    return textWatcher
}

inline fun EditText.afterTextChanged(crossinline afterTextChanged: (string: Editable) -> Unit): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) = afterTextChanged(s)

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit
    }
    addTextChangedListener(textWatcher)
    return textWatcher

}

inline fun TextInputEditText.onTextChanged(crossinline onTextChanged: (string: CharSequence) -> Unit): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) = Unit

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = onTextChanged(s)
    }
    addTextChangedListener(textWatcher)
    return textWatcher

}

inline fun EditText.onTextChanged(crossinline onTextChanged: (string: CharSequence) -> Unit): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) = Unit

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = onTextChanged(s)
    }
    addTextChangedListener(textWatcher)
    return textWatcher
}

inline fun View.onClick(delayMillis: Long = 500, crossinline clickListener: (View) -> Unit) {
    var clickMillis = 0L
    setOnClickListener {
        val elapsedRealTime = SystemClock.elapsedRealtime()
        if (elapsedRealTime > clickMillis) {
            clickMillis = elapsedRealTime + delayMillis
            clickListener.invoke(it)
        }
    }
}

inline fun View.onLongClick(delayMillis: Long = 500, crossinline clickListener: (View) -> Unit) {
    var clickMillis = 0L
    setOnLongClickListener {
        val elapsedRealTime = SystemClock.elapsedRealtime()
        if (elapsedRealTime > clickMillis) {
            clickMillis = elapsedRealTime + delayMillis
            clickListener.invoke(it)
        }
        true
    }
}

inline fun Group.setAllOnClick(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

inline fun View.snakeMessage(
    message: String?,
    length: Int = Snackbar.LENGTH_LONG,
    @ColorInt
    bgColor: Int = Color.RED,
    @ColorInt
    textColor: Int = Color.WHITE
) {
    Snackbar
        .make(this, message ?: "", length)
        .setBackgroundTint(bgColor)
        .setTextColor(textColor)
        .show()
}