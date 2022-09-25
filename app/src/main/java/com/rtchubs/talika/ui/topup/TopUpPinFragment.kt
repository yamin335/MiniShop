package com.rtchubs.talika.ui.topup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.rtchubs.talika.R
import com.rtchubs.talika.BR
import com.rtchubs.talika.databinding.TopUpPinFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment
import com.rtchubs.talika.util.hideKeyboard

class TopUpPinFragment : BaseFragment<TopUpPinFragmentBinding, TopUpPinViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_topup_pin
    override val viewModel: TopUpPinViewModel by viewModels {
        viewModelFactory
    }

    val args: TopUpAmountFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStatusBarBackgroundColor("#1E4356")
        registerToolbar(viewDataBinding.toolbar)

        val helper = args.topUpHelper

        viewModel.pin.observe(viewLifecycleOwner, Observer {  pin ->
            pin?.let {
                helper.mobile = pin
                viewDataBinding.btnProceed.isEnabled = (it.length == 6)
            }
        })

        viewDataBinding.btnProceed.setOnClickListener {
            hideKeyboard()
            navController.navigate(TopUpPinFragmentDirections.actionTopUpPinFragmentToHome2Fragment())
        }
    }
}