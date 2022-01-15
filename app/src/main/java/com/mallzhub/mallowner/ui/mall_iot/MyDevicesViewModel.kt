package com.mallzhub.mallowner.ui.mall_iot

import android.app.Application
import com.mallzhub.mallowner.ui.common.BaseViewModel
import javax.inject.Inject

class MyDevicesViewModel @Inject constructor(
    private val application: Application
) : BaseViewModel(application) {

}