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

    override fun createAccount(login: String, password: String, photo: ByteArray?) {
        // TODO: 3/13/21 realize
    }

    override fun showMediaDialog() = view.onShowMediaDialog()
}