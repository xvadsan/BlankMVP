package com.xvadsan.blankmvp.ui.common

import com.xvadsan.blankmvp.base.BaseContract

interface CommonContract {

    interface View : BaseContract.View {
        fun onShowLoad()
        fun onHideLoad()
        fun showError(throwable: Throwable)
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
    }
}