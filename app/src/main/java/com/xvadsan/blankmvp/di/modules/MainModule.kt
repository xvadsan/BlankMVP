package com.xvadsan.blankmvp.di.modules

import com.xvadsan.blankmvp.di.scopes.MainScope
import com.xvadsan.blankmvp.ui.main.MainContract
import com.xvadsan.blankmvp.ui.main.MainPresenter
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @MainScope
    @Binds
    fun presenter(presenter: MainPresenter): MainContract.Presenter

}