package com.rtchubs.talika.ui.add_payment_methods

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.AddBankFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment
import com.rtchubs.talika.util.AppConstants
import com.rtchubs.talika.util.showSuccessToast
import com.rtchubs.talika.util.showWarningToast

class AddBankFragment : BaseFragment<AddBankFragmentBinding, AddBankViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_add_bank
    override val viewModel: AddBankViewModel by viewModels {
        viewModelFactory
    }

    val args: AddBankFragmentArgs by navArgs()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mActivity.window?.setSoftInputMode(
//            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//        )
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        requireActivity().window?.setSoftInputMode(
//            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
//        )
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStatusBarBackgroundColor("#1E4356")
        registerToolbar(viewDataBinding.toolbar)

        viewModel.bankId = args.selectedBank.id ?: 0

        viewModel.bankAccount.observe(viewLifecycleOwner, Observer { accountNummber ->
            accountNummber?.let { validAccountNumber ->
                viewDataBinding.btnAdd.isEnabled = validAccountNumber.length >= 10
            }
        })

        viewModel.addBankAccountResponse.observe(viewLifecycleOwner, Observer { response ->
            response?.let { validResponse ->
                when {
                    validResponse.isSuccess == true && validResponse.body?.isSuccess == true-> {
                        showSuccessToast(mContext, validResponse.body.message ?: AppConstants.saveSuccessfulMessage)
                        navController.navigate(AddBankFragmentDirections.actionAddBankFragmentToHome2Fragment())
                    }
                    validResponse.isSuccess == false && validResponse.errorMessage != null -> {
                        showWarningToast(mContext, validResponse.errorMessage)
                    }
                    else -> {
                        showWarningToast(mContext, AppConstants.commonErrorMessage)
                    }
                }
            }
        })
    }
}