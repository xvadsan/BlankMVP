package com.xvadsan.blankmvp.base.di.modules

import com.xvadsan.blankmvp.base.di.scopes.StartScope
import com.xvadsan.blankmvp.ui.start.StartContract
import com.xvadsan.blankmvp.ui.start.StartPresenter
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import com.xvadsan.blankmvp.ui.switcher.ViewStateSwitcher
import dagger.Binds
import dagger.Module

@Module
interface StartModule {

    @StartScope
    @Binds
    fun presenter(presenter: StartPresenter): StartContract.Presenter

    @StartScope
    @Binds
    fun switcher(stateSwitcher: ViewStateSwitcher): StateViewSwitcher
}