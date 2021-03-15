package com.xvadsan.blankmvp.ui.create

import com.xvadsan.blankmvp.base.BaseContract

interface CreateContract {

    interface View : BaseContract.View {
        fun onShowLoad()
        fun onHideLoad()
        fun showError(throwable: Throwable)
        fun onShowMediaDialog()
        fun onShowSuccessMessage()
        fun onBack()
        fun onToast(mes: String)
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
        fun createAccountWithPhoto(login: String, password:String, photo: ByteArray)
        fun showMediaDialog()
        fun toast(mes: String)
        fun back()
    }
}