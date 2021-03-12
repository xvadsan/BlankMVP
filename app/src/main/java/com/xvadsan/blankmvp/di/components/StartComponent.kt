package com.xvadsan.blankmvp.di.components

import com.xvadsan.blankmvp.di.modules.StartModule
import com.xvadsan.blankmvp.di.scopes.StartScope
import com.xvadsan.blankmvp.ui.start.StartFragment
import dagger.Subcomponent

@StartScope
@Subcomponent(modules = [StartModule::class])
interface StartComponent {
    fun inject(fragment: StartFragment)
}