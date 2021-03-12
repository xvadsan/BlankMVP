package com.xvadsan.blankmvp.ui.common

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseFragment
import com.xvadsan.blankmvp.databinding.FragmentCommonBinding
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import kotlinx.android.synthetic.main.fragment_common.*
import javax.inject.Inject

class CommonFragment : BaseFragment<CommonContract.Presenter>(R.layout.fragment_common), CommonContract.View {

    @Inject
    lateinit var stateSwitcher: StateViewSwitcher
    private val binding by viewBinding(FragmentCommonBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        stateSwitcher.init(stateContainer)
        onInitUi()
    }

    private fun onInitUi() = with(binding) {
        /* no-op */
    }

    override fun showError(throwable: Throwable) = Toast.makeText(requireContext(), throwable.message.toString(), Toast.LENGTH_SHORT).show()

    override fun onShowLoad() = stateSwitcher.switchToLoading()

    override fun onHideLoad() = stateSwitcher.switchToMain()

    override fun onDestroyView() {
        super.onDestroyView()
        stateSwitcher.destroy()
    }

    override fun createComponent() = App.instance.getAppComponent().createCommonFragment().inject(this)
}