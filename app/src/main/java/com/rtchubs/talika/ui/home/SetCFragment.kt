package com.rtchubs.talika.ui.home

import androidx.fragment.app.viewModels
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.SetCFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment

class SetCFragment : BaseFragment<SetCFragmentBinding, SetCViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_set_c

    override val viewModel: SetCViewModel by viewModels { viewModelFactory }
}