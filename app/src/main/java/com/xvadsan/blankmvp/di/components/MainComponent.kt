package com.xvadsan.blankmvp.di.components

import com.xvadsan.blankmvp.di.modules.MainModule
import com.xvadsan.blankmvp.di.scopes.MainScope
import com.xvadsan.blankmvp.ui.main.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}