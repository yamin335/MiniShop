package com.rtchubs.talika.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rtchubs.talika.R
import com.rtchubs.talika.BR
import com.rtchubs.talika.databinding.SettingsFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment

class SettingsFragment : BaseFragment<SettingsFragmentBinding, SettingsViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_settings
    override val viewModel: SettingsViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToolbar(viewDataBinding.toolbar)
    }
}