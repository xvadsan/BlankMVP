package com.xvadsan.blankmvp.ui.create

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.request.RequestOptions
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseFragment
import com.xvadsan.blankmvp.base.extensions.onClick
import com.xvadsan.blankmvp.data.image.GlideApp
import com.xvadsan.blankmvp.databinding.FragmentCreateBinding
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import com.xvadsan.blankmvp.util.FileUtils
import com.xvadsan.blankmvp.util.ImagePicker
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_inner_create.*
import javax.inject.Inject

class CreateFragment : BaseFragment<CreateContract.Presenter>(R.layout.fragment_create), CreateContract.View {

    @Inject
    lateinit var stateSwitcher: StateViewSwitcher
    private val binding by viewBinding(FragmentCreateBinding::bind)
    private lateinit var navController: NavController
    private var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        navController = view.findNavController()
        stateSwitcher.init(stateContainer)
        onInitUi()
    }

    private fun onInitUi() = with(binding) {
        val pickImage = ImagePicker(requireActivity().activityResultRegistry) { uri ->
            imageUri = uri
            setChatAvatar(uri)
        }
        ivBackBtn.onClick { navController.popBackStack() }
        btnCreate.onClick { onCreateAccount() }
        ivPhoto.onClick { pickImage.pickImage() }
    }

    private fun onCreateAccount() {
        if (etUsername.text.toString().isEmpty() || etPassword.text.toString().isEmpty() || getPhoto() == null || getPhoto().contentEquals(ByteArray(0))) {
            Toast.makeText(requireContext(), getString(R.string.create_error), Toast.LENGTH_SHORT).show()
            return
        }
        presenter.createAccount(
            login = etUsername.text.toString(),
            password = etPassword.text.toString(),
            photo = getPhoto()
        )
    }

    private fun getPhoto(): ByteArray? {
        if (imageUri == null) return ByteArray(0)
        val bmpPhoto = FileUtils().getBitmapFromUri(requireContext(), imageUri)
        return bmpPhoto?.let { FileUtils().getImageByteArray(bmp = it) }
    }

    private fun setChatAvatar(imageUri: Uri?) {
        GlideApp.with(this)
            .asBitmap()
            .load(imageUri)
            .apply { RequestOptions().circleCrop() }
            .placeholder(R.drawable.ic_preview_empty)
            .circleCrop()
            .into(binding.ivPhoto)
    }

    override fun showError(throwable: Throwable) = Toast.makeText(requireContext(), throwable.message.toString(), Toast.LENGTH_SHORT).show()

    override fun onShowLoad() = stateSwitcher.switchToLoading()

    override fun onHideLoad() = stateSwitcher.switchToMain()

    override fun onDestroyView() {
        super.onDestroyView()
        stateSwitcher.destroy()
    }

    override fun createComponent() = App.instance.getAppComponent().createCreateFragment().inject(this)
}