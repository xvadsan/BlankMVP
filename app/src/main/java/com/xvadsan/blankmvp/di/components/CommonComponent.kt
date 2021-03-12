package com.xvadsan.blankmvp.di.components

import com.xvadsan.blankmvp.di.modules.CommonModule
import com.xvadsan.blankmvp.di.scopes.CommonScope
import com.xvadsan.blankmvp.ui.common.CommonFragment
import dagger.Subcomponent

@CommonScope
@Subcomponent(modules = [CommonModule::class])
interface CommonComponent {
    fun inject(fragment: CommonFragment)
}