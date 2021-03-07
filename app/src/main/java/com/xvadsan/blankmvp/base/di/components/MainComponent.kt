package com.xvadsan.blankmvp.base.di.components

import com.xvadsan.blankmvp.base.di.modules.MainModule
import com.xvadsan.blankmvp.base.di.scopes.MainScope
import com.xvadsan.blankmvp.ui.main.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}