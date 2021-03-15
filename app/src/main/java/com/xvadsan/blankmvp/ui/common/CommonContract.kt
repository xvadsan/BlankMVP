package com.xvadsan.blankmvp.ui.common

import com.xvadsan.blankmvp.base.BaseContract
import com.xvadsan.blankmvp.domain.models.ProfileModel

interface CommonContract {

    interface View : BaseContract.View {
        fun onShowLoad()
        fun onHideLoad()
        fun showError(throwable: Throwable)
        fun onCloseApp()
        fun onSetData(profiles: List<ProfileModel>)
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
        fun closeApp()
    }
}