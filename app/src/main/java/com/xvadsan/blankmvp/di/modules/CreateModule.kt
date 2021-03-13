package com.xvadsan.blankmvp.di.modules

import com.xvadsan.blankmvp.di.scopes.CreateScope
import com.xvadsan.blankmvp.ui.create.CreateContract
import com.xvadsan.blankmvp.ui.create.CreatePresenter
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import com.xvadsan.blankmvp.ui.switcher.ViewStateSwitcher
import dagger.Binds
import dagger.Module

@Module
interface CreateModule {

    @CreateScope
    @Binds
    fun presenter(presenter: CreatePresenter): CreateContract.Presenter

    @CreateScope
    @Binds
    fun switcher(stateSwitcher: ViewStateSwitcher): StateViewSwitcher
}