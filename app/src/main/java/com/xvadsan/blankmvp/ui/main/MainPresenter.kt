package com.xvadsan.blankmvp.ui.main

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.di.scopes.MainScope
import javax.inject.Inject

@MainScope
class MainPresenter @Inject constructor(
    /* no-op */
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override lateinit var view: MainContract.View

    override fun start() = Unit
}