package com.xvadsan.blankmvp.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter {

    protected var disposables = CompositeDisposable()

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun stop() = disposables.clear()

    override fun dispose() = disposables.dispose()
}