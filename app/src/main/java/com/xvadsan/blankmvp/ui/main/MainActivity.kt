package com.xvadsan.blankmvp.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseActivity

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.mainFragment)
        presenter.view = this
    }

    override fun createComponent() = App.instance.getAppComponent().createMainActivity().inject(this)
}