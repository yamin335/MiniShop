package com.rtchubs.talika.ui.exams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rtchubs.talika.R
import com.rtchubs.talika.BR
import com.rtchubs.talika.databinding.ExamsFragmentBinding
import com.rtchubs.talika.ui.common.BaseFragment

class ExamsFragment : BaseFragment<ExamsFragmentBinding, ExamsViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_exams
    override val viewModel: ExamsViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}