package com.mallzhub.mallowner.ui.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.databinding.InfoFragmentBinding
import com.mallzhub.mallowner.ui.common.BaseFragment

class InfoFragment : BaseFragment<InfoFragmentBinding, InfoViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_info
    override val viewModel: InfoViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}