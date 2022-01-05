package com.mallzhub.mallowner.ui.setup_complete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.databinding.SetupCompleteBinding
import com.mallzhub.mallowner.ui.common.BaseFragment

class SetupCompleteFragment  : BaseFragment<SetupCompleteBinding, SetupCompleteViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_setup_complete
    override val viewModel: SetupCompleteViewModel by viewModels { viewModelFactory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.btnContinue.setOnClickListener {
        }
    }

}