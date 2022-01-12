package com.mallzhub.mallowner.ui.shops

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mallzhub.mallowner.local_db.dao.CartDao
import com.mallzhub.mallowner.repos.HomeRepository
import com.mallzhub.mallowner.ui.common.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ShopDetailViewModel @Inject constructor(
    private val application: Application
) : BaseViewModel(application) {
}