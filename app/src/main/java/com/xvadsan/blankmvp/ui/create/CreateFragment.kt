package com.xvadsan.blankmvp.ui.create

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.request.RequestOptions
import com.xvadsan.blankmvp.App
import com.xvadsan.blankmvp.BuildConfig
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.base.BaseFragment
import com.xvadsan.blankmvp.base.extensions.*
import com.xvadsan.blankmvp.data.image.GlideApp
import com.xvadsan.blankmvp.databinding.FragmentCreateBinding
import com.xvadsan.blankmvp.ui.dialogs.media.MediaDialog
import com.xvadsan.blankmvp.ui.switcher.StateViewSwitcher
import com.xvadsan.blankmvp.util.FileUtils
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
        ivBackBtn.onClick { presenter.back() }
        btnCreate.onClick { onCreateAccount() }
        ivPhoto.onClick { presenter.showMediaDialog() }
    }

    private fun onCreateAccount() {
        if (etUsername.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty()) {
            requireActivity().hideKeyboard(requireView())
            presenter.createAccountWithPhoto(login = etUsername.text.toString(), password = etPassword.text.toString(), photo = getPhoto())
        } else {
            presenter.toast(getString(R.string.create_error))
        }
    }

    override fun onShowMediaDialog() {
        val dialog = MediaDialog()
        dialog.onSetListener(object : MediaDialog.Listener {
            override fun onClickCamera() = takePhotoAndPermissions()
            override fun onClickGallery() = takePicAndPermissions()
        })
        dialog.isCancelable = true
        dialog.show(requireActivity().supportFragmentManager, TAG_MEDIA_DIALOG)
    }

    private fun takePhotoAndPermissions() {
        permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA) {
            this.allGranted {
                imageUri = FileProvider.getUriForFile(requireContext(), "${BuildConfig.APPLICATION_ID}.fileprovider", FileUtils().createPhoto(requireContext()))
                takePhoto(imageUri) { success ->
                    if (success == true)
                        setChatAvatar(imageUri = imageUri)
                }
            }
            this.denied {
                presenter.toast(getString(R.string.create_permission_error))
            }
        }
    }

    private fun takePicAndPermissions() {
        permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            this.allGranted {
                getContent(IMAGE_MIME_TYPE) { uri ->
                    imageUri = uri
                    setChatAvatar(imageUri = imageUri)
                }
            }
            this.denied {
                presenter.toast(getString(R.string.create_permission_error))
            }
        }
    }

    private fun getPhoto(): ByteArray {
        if (imageUri == null) return ByteArray(0)
        val resized = FileUtils().photoResize(requireContext(), imageUri) ?: return ByteArray(0)
        return FileUtils().getImageByteArray(bmp = resized)
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

    override fun onToast(mes: String) = Toast.makeText(requireContext(), mes, Toast.LENGTH_SHORT).show()

    override fun onShowSuccessMessage() = Toast.makeText(requireContext(), getString(R.string.create_success_create), Toast.LENGTH_SHORT).show()

    override fun showError(throwable: Throwable) = Toast.makeText(requireContext(), throwable.message.toString(), Toast.LENGTH_SHORT).show()

    override fun onShowLoad() = stateSwitcher.switchToLoading()

    override fun onHideLoad() = stateSwitcher.switchToMain()

    override fun onBack() {
        navController.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stateSwitcher.destroy()
    }

    override fun createComponent() = App.instance.getAppComponent().createCreateFragment().inject(this)

    companion object {
        private const val IMAGE_MIME_TYPE = "image/*"
        private const val TAG_MEDIA_DIALOG = "mediaDialog"
    }
}