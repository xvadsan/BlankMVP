package com.xvadsan.blankmvp.base.di.components

import com.xvadsan.blankmvp.base.di.modules.CommonModule
import com.xvadsan.blankmvp.base.di.scopes.CommonScope
import com.xvadsan.blankmvp.ui.common.CommonFragment
import dagger.Subcomponent

@CommonScope
@Subcomponent(modules = [CommonModule::class])
interface CommonComponent {
    fun inject(fragment: CommonFragment)
}