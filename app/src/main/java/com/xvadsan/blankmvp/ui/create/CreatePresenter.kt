package com.xvadsan.blankmvp.ui.create

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.di.scopes.CommonScope
import com.xvadsan.blankmvp.di.scopes.CreateScope
import javax.inject.Inject

@CreateScope
class CreatePresenter @Inject constructor(
    /* no-op */
) : BasePresenter<CreateContract.View>(), CreateContract.Presenter {

    override lateinit var view: CreateContract.View

    override fun start() = Unit
}