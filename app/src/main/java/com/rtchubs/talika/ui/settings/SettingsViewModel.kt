package com.rtchubs.talika.ui.settings

import android.app.Application
import com.rtchubs.talika.ui.common.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val application: Application
) : BaseViewModel(application) {

}