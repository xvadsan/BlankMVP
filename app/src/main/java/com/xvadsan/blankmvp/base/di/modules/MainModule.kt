package com.xvadsan.blankmvp.base.di.modules

import com.xvadsan.blankmvp.base.di.scopes.MainScope
import com.xvadsan.blankmvp.ui.main.MainContract
import com.xvadsan.blankmvp.ui.main.MainPresenter
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import com.xvadsan.blankmvp.ui.switcher.ViewStateSwitcher
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @MainScope
    @Binds
    fun presenter(presenter: MainPresenter): MainContract.Presenter

}