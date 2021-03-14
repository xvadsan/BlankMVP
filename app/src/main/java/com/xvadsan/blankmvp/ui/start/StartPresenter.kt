package com.xvadsan.blankmvp.ui.start

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.di.scopes.StartScope
import com.xvadsan.blankmvp.domain.users.GetUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@StartScope
class StartPresenter @Inject constructor(
    private val getUser: GetUser
) : BasePresenter<StartContract.View>(), StartContract.Presenter {

    override lateinit var view: StartContract.View

    override fun start() = Unit

    override fun login(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            disposables += getUser.execute(GetUser.Param(login, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.onShowLoad() }
                .doAfterTerminate { view.onHideLoad() }
                .subscribe({ view.onShowCommonFragment() }, { view.showError(it) })
        } else {
            view.onShowLoginError()
        }
    }

    override fun showCommonFragment() = view.onShowCommonFragment()

    override fun showCreateFragment() = view.onShowCreateFragment()
}