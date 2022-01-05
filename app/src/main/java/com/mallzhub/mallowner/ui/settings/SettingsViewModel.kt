package com.mallzhub.mallowner.ui.settings

import android.app.Application
import com.mallzhub.mallowner.ui.common.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val application: Application
) : BaseViewModel(application) {

}