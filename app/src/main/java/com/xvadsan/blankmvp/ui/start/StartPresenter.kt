package com.xvadsan.blankmvp.ui.start

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.base.di.scopes.StartScope
import javax.inject.Inject

@StartScope
class StartPresenter @Inject constructor(
    /* no-op */
) : BasePresenter<StartContract.View>(), StartContract.Presenter {

    override lateinit var view: StartContract.View

    override fun start() = Unit

    override fun showCommonFragment() = view.onShowCommonFragment()
}