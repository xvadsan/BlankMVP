package com.xvadsan.blankmvp.base.di.components

import com.xvadsan.blankmvp.base.di.modules.StartModule
import com.xvadsan.blankmvp.base.di.scopes.StartScope
import com.xvadsan.blankmvp.ui.start.StartFragment
import dagger.Subcomponent

@StartScope
@Subcomponent(modules = [StartModule::class])
interface StartComponent {
    fun inject(fragment: StartFragment)
}