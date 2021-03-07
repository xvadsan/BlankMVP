package com.xvadsan.blankmvp.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseFragment
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : BaseFragment<StartContract.Presenter>(), StartContract.View {

    @Inject
    lateinit var stateSwitcher: StateViewSwitcher

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        stateSwitcher.init(stateContainer)
    }

    override fun showError(throwable: Throwable) = Toast.makeText(requireContext(), throwable.message.toString(), Toast.LENGTH_SHORT).show()

    override fun onShowLoad() = stateSwitcher.switchToLoading()

    override fun onHideLoad() = stateSwitcher.switchToMain()

    override fun onDestroyView() {
        super.onDestroyView()
        stateSwitcher.destroy()
    }

    override fun createComponent() = App.instance.getAppComponent().createStartFragment().inject(this)
}