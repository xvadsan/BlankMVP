package com.xvadsan.blankmvp.ui.common

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.base.di.scopes.CommonScope
import com.xvadsan.blankmvp.base.di.scopes.StartScope
import javax.inject.Inject

@CommonScope
class CommonPresenter @Inject constructor(
    /* no-op */
) : BasePresenter<CommonContract.View>(), CommonContract.Presenter {

    override lateinit var view: CommonContract.View

    override fun start() = Unit
}