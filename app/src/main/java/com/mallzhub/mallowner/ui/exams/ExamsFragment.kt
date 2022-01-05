package com.mallzhub.mallowner.ui.exams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.databinding.ExamsFragmentBinding
import com.mallzhub.mallowner.ui.common.BaseFragment

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