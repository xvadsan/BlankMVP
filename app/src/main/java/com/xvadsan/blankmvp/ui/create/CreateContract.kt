package com.xvadsan.blankmvp.ui.create

import com.xvadsan.blankmvp.base.BaseContract

interface CreateContract {

    interface View : BaseContract.View {
        fun onShowLoad()
        fun onHideLoad()
        fun showError(throwable: Throwable)
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
    }
}