package com.xvadsan.blankmvp.ui.common

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.di.scopes.CommonScope
import com.xvadsan.blankmvp.domain.UseCase
import com.xvadsan.blankmvp.domain.models.ProfileModel
import com.xvadsan.blankmvp.domain.users.GetUsers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@CommonScope
class CommonPresenter @Inject constructor(
    private val getUsers: GetUsers
) : BasePresenter<CommonContract.View>(), CommonContract.Presenter {

    override lateinit var view: CommonContract.View

    override fun start() {
        disposables += getUsers.execute(UseCase.None)
            .toObservable()
            .flatMapIterable { it }
            .map {
                ProfileModel(
                    id = it.user.id,
                    login = it.user.login,
                    password = it.user.password,
                    photo = it.photo?.photo
                )
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.onShowLoad() }
            .doAfterTerminate { view.onHideLoad() }
            .subscribe({ view.onSetData(it) }, { view.showError(it) })
    }

    override fun closeApp() = view.onCloseApp()
}