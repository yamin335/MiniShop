package com.rtchubs.engineerbooks.ui.home

import android.app.Application
import com.rtchubs.engineerbooks.ui.common.BaseViewModel
import javax.inject.Inject

class MoreBookListViewModel @Inject constructor(
    private val application: Application
) : BaseViewModel(application) {

}