package com.xvadsan.blankmvp.ui.start

import com.xvadsan.blankmvp.base.BaseContract

interface StartContract {

    interface View : BaseContract.View {
        fun onShowLoad()
        fun onHideLoad()
        fun showError(throwable: Throwable)
        fun onShowCommonFragment()
        fun onShowCreateFragment()
        fun onShowLoginError()
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
        fun showCommonFragment()
        fun showCreateFragment()
        fun login(login: String, password: String)
    }
}