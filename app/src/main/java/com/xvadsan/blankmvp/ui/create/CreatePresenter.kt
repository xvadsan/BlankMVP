package com.xvadsan.blankmvp.ui.create

import com.xvadsan.blankmvp.base.BasePresenter
import com.xvadsan.blankmvp.di.scopes.CreateScope
import com.xvadsan.blankmvp.domain.users.AddPhoto
import com.xvadsan.blankmvp.domain.users.AddUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@CreateScope
class CreatePresenter @Inject constructor(
    private val addUser: AddUser,
    private val addPhoto: AddPhoto
) : BasePresenter<CreateContract.View>(), CreateContract.Presenter {

    override lateinit var view: CreateContract.View

    override fun start() = Unit

    override fun createAccountWithPhoto(login: String, password: String, photo: ByteArray) {
        disposables += addUser.execute(AddUser.Param(login, password))
            .andThen(addPhoto.execute(AddPhoto.Param(photo)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.onShowLoad() }
            .doOnComplete {
                view.onHideLoad()
                view.onShowSuccessMessage()
            }
            .subscribe({ view.onBack() }, { view.showError(it) })
    }

    override fun showMediaDialog() = view.onShowMediaDialog()

    override fun back() = view.onBack()
}