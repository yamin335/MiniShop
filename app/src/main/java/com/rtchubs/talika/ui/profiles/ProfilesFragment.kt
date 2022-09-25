package com.rtchubs.talika.ui.profiles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.ProfileFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment

class ProfilesFragment : BaseFragment<ProfileFragmentBinding, ProfilesViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: ProfilesViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToolbar(viewDataBinding.toolbar)

    }
}