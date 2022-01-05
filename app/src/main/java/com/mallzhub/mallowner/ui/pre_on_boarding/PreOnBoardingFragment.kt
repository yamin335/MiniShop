package com.mallzhub.mallowner.ui.pre_on_boarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.databinding.PreOnBoardBinding
import com.mallzhub.mallowner.ui.common.BaseFragment

class PreOnBoardingFragment() : BaseFragment<PreOnBoardBinding, PreOnBoardingViewModel>() {
    override val bindingVariable: Int
        get() = BR._all
    override val layoutId: Int
        get() = R.layout.fragment_pre_onbarding
    override val viewModel: PreOnBoardingViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStatusBarBackgroundColor("#689F38")
        viewDataBinding.btnOnboardingStart.setOnClickListener {
        }
    }
}