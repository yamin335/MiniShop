package com.mallzhub.mallowner.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.databinding.LayoutOperatorSelectionBinding
import com.mallzhub.mallowner.databinding.SignInBinding
import com.mallzhub.mallowner.models.registration.LoginRequestBody
import com.mallzhub.mallowner.models.registration.RegistrationHelperModel
import com.mallzhub.mallowner.ui.LoginHandlerCallback
import com.mallzhub.mallowner.ui.common.BaseFragment
import com.mallzhub.mallowner.util.AppConstants.commonErrorMessage
import com.mallzhub.mallowner.util.hideKeyboard
import com.mallzhub.mallowner.util.showErrorToast

class SignInFragment : BaseFragment<SignInBinding, SignInViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_sign_in
    override val viewModel: SignInViewModel by viewModels {
        viewModelFactory
    }

    private var loginListener: LoginHandlerCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginHandlerCallback) {
            loginListener = context
        } else {
            throw RuntimeException("$context must implement LoginHandlerCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()
        loginListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
        // This callback will only be called when HomeFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this, true) {
            requireActivity().finish()
            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStatusBarBackgroundColor("#161E2C")
        //registerToolbar(viewDataBinding.toolbar)

        viewModel.email.observe(viewLifecycleOwner, Observer {  mobileNo ->
            mobileNo?.let {
                viewDataBinding.btnProceed.isEnabled = it.isNotBlank() && !viewModel.password.value.isNullOrBlank()
            }
        })

        viewModel.password.observe(viewLifecycleOwner, Observer {  mobileNo ->
            mobileNo?.let {
                viewDataBinding.btnProceed.isEnabled = it.isNotBlank() && !viewModel.email.value.isNullOrBlank()
            }
        })

        viewDataBinding.btnProceed.setOnClickListener {
            hideKeyboard()
            val requestBody = LoginRequestBody(viewModel.email.value, viewModel.password.value,
                "0", "malllogin", "Mall Owner")
            viewModel.signIn(requestBody).observe(viewLifecycleOwner, Observer { data ->
                data?.response?.let {
                    if (data.success == true) {
                        preferencesHelper.saveMallOwner(it)
                        preferencesHelper.isLoggedIn = true
                        loginListener?.onLoggedIn()
                    }
                }
            })
        }
    }
}