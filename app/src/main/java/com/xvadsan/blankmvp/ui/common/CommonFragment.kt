package com.xvadsan.blankmvp.ui.common

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseFragment
import com.xvadsan.blankmvp.base.extensions.onClick
import com.xvadsan.blankmvp.databinding.FragmentCommonBinding
import com.xvadsan.blankmvp.domain.models.ProfileModel
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class CommonFragment : BaseFragment<CommonContract.Presenter>(R.layout.fragment_common), CommonContract.View {

    @Inject
    lateinit var stateSwitcher: StateViewSwitcher
    private val binding by viewBinding(FragmentCommonBinding::bind)
    lateinit var adapter: CommonAdapter
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        navController = view.findNavController()
        stateSwitcher.init(stateContainer)
        presenter.start()
        onInitUi()
    }

    private fun onInitUi() = with(binding) {
        ivBack.onClick { presenter.closeApp() }
        adapter = CommonAdapter(object : CommonAdapter.Listener {
            override fun onClick(model: ProfileModel) = Toast.makeText(requireContext(), model.login, Toast.LENGTH_SHORT).show()
        })
        rvCommon.adapter = adapter
    }

    override fun onCloseApp() = requireActivity().finish()

    override fun onSetData(profiles: List<ProfileModel>) = adapter.setItems(profiles)

    override fun showError(throwable: Throwable) = Toast.makeText(requireContext(), throwable.message.toString(), Toast.LENGTH_SHORT).show()

    override fun onShowLoad() = stateSwitcher.switchToLoading()

    override fun onHideLoad() = stateSwitcher.switchToMain()

    override fun onDestroyView() {
        super.onDestroyView()
        stateSwitcher.destroy()
    }

    override fun createComponent() = App.instance.getAppComponent().createCommonFragment().inject(this)
}