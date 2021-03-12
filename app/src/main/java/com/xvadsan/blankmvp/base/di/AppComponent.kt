package com.xvadsan.blankmvp.base.di

import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.base.di.components.CommonComponent
import com.xvadsan.blankmvp.base.di.components.MainComponent
import com.xvadsan.blankmvp.base.di.components.StartComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(application: App)

    fun createMainActivity(): MainComponent

    fun createStartFragment(): StartComponent

    fun createCommonFragment(): CommonComponent

}