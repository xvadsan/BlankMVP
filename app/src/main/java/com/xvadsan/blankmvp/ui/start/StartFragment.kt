package com.xvadsan.blankmvp.ui.start

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseFragment
import com.xvadsan.blankmvp.base.extensions.hideKeyboard
import com.xvadsan.blankmvp.base.extensions.onClick
import com.xvadsan.blankmvp.databinding.FragmentStartBinding
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : BaseFragment<StartContract.Presenter>(R.layout.fragment_start), StartContract.View {

    @Inject
    lateinit var stateSwitcher: StateViewSwitcher
    private val binding by viewBinding(FragmentStartBinding::bind)
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        navController = view.findNavController()
        stateSwitcher.init(stateContainer)
        onInitUi()
    }

    private fun onInitUi() = with(binding) {
        etUsername.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                motionLayout.transitionToEnd()
            }
        }
        etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                motionLayout.transitionToEnd()
            }
        }
        tvCreateAccount.onClick { presenter.showCreateFragment() }
        btnAuthLogin.onClick { presenter.login(etUsername.text.toString(), etPassword.text.toString()) }
    }

    override fun onShowCommonFragment() {
        requireActivity().hideKeyboard(requireView())
        navController.popBackStack(R.id.commonFragment, true)
        navController.navigate(R.id.commonFragment)
    }

    override fun onShowCreateFragment() = navController.navigate(R.id.createFragment)

    override fun onShowLoginError() = Toast.makeText(requireContext(), getString(R.string.start_auth_error), Toast.LENGTH_SHORT).show()

    override fun showError(throwable: Throwable) {
        if (throwable.message?.contains(getString(R.string.start_user_not_found)) == true) {
            onShowLoginError()
            return
        }
        Toast.makeText(requireContext(), throwable.message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onShowLoad() = stateSwitcher.switchToLoading()

    override fun onHideLoad() = stateSwitcher.switchToMain()

    override fun onDestroyView() {
        super.onDestroyView()
        stateSwitcher.destroy()
    }

    override fun createComponent() = App.instance.getAppComponent().createStartFragment().inject(this)
}