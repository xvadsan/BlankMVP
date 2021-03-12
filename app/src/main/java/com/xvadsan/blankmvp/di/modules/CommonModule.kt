package com.xvadsan.blankmvp.di.modules

import com.xvadsan.blankmvp.di.scopes.CommonScope
import com.xvadsan.blankmvp.ui.common.CommonContract
import com.xvadsan.blankmvp.ui.common.CommonPresenter
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import com.xvadsan.blankmvp.ui.switcher.ViewStateSwitcher
import dagger.Binds
import dagger.Module

@Module
interface CommonModule {

    @CommonScope
    @Binds
    fun presenter(presenter: CommonPresenter): CommonContract.Presenter

    @CommonScope
    @Binds
    fun switcher(stateSwitcher: ViewStateSwitcher): StateViewSwitcher
}