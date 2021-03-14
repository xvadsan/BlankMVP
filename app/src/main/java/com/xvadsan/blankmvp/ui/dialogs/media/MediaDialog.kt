package com.xvadsan.blankmvp.ui.dialogs.media

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.extensions.onClick
import kotlinx.android.synthetic.main.dialog_media.view.*


class MediaDialog : DialogFragment() {

    interface Listener {
        fun onClickCamera()
        fun onClickGallery()
    }

    private lateinit var listener: Listener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_media, null, false)
        val builder = AlertDialog.Builder(requireContext(), R.style.MyRoundedCornersDialog)
        builder.setView(view)
        initUi(view)
        return builder.create()
    }

    private fun initUi(view: View) {
        view.tvCamera.onClick {
            this.listener.onClickCamera()
            this.dismiss()
        }
        view.tvGallery.onClick {
            this.listener.onClickGallery()
            this.dismiss()
        }
    }

    fun onSetListener(listener: Listener) {
        this.listener = listener
    }
}