package com.rtchubs.talika.ui.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rtchubs.talika.R
import com.rtchubs.talika.BR
import com.rtchubs.talika.databinding.InfoFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment

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