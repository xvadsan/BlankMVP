package com.xvadsan.blankmvp.di.components

import com.xvadsan.blankmvp.di.modules.CreateModule
import com.xvadsan.blankmvp.di.scopes.CreateScope
import com.xvadsan.blankmvp.ui.create.CreateFragment
import dagger.Subcomponent

@CreateScope
@Subcomponent(modules = [CreateModule::class])
interface CreateComponent {
    fun inject(fragment: CreateFragment)
}