package com.xvadsan.blankmvp.ui.main

import com.xvadsan.blankmvp.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        /* no-op */
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
    }
}