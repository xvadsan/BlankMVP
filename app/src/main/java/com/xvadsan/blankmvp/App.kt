package com.xvadsan.blankmvp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.xvadsan.blankmvp.di.AppComponent
import com.xvadsan.blankmvp.di.AppModule
import com.xvadsan.blankmvp.di.DaggerAppComponent
import io.reactivex.disposables.CompositeDisposable

class App : Application() {

    private lateinit var appComponent: AppComponent
    private val disposables = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupDagger()
        setupOutsideLibs()
    }

    fun getAppComponent(): AppComponent = appComponent

    private fun setupDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext, this))
            .build()
        appComponent.inject(this)
    }

    private fun setupOutsideLibs() {
        AndroidThreeTen.init(this)
    }

    override fun onTerminate() {
        disposables.dispose()
        super.onTerminate()
    }

    companion object {
        lateinit var instance: App private set
    }
}