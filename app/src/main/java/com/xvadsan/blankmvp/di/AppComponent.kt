package com.xvadsan.blankmvp.di

import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.data.image.AppGlideModule
import com.xvadsan.blankmvp.di.components.CommonComponent
import com.xvadsan.blankmvp.di.components.MainComponent
import com.xvadsan.blankmvp.di.components.StartComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    fun inject(application: App)

    fun createMainActivity(): MainComponent

    fun createStartFragment(): StartComponent

    fun createCommonFragment(): CommonComponent

}