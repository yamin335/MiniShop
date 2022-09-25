package com.rtchubs.talika.ui.home

import androidx.fragment.app.viewModels
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.SetBFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment

class SetBFragment : BaseFragment<SetBFragmentBinding, SetBViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_set_b

    override val viewModel: SetBViewModel by viewModels { viewModelFactory }
}