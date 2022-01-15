package com.mallzhub.mallowner.ui.mall_iot

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mallzhub.mallowner.api.*
import com.mallzhub.mallowner.local_db.dao.CartDao
import com.mallzhub.mallowner.models.AllMerchantResponse
import com.mallzhub.mallowner.models.MallMerchant
import com.mallzhub.mallowner.models.ShoppingMallRequestBody
import com.mallzhub.mallowner.models.ShoppingMallResponse
import com.mallzhub.mallowner.repos.HomeRepository
import com.mallzhub.mallowner.ui.common.BaseViewModel
import com.mallzhub.mallowner.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MallIOTViewModel @Inject constructor(
    private val application: Application,
    private val repository: HomeRepository
) : BaseViewModel(application) {

    val shoppingMallResponse: MutableLiveData<List<MallMerchant>> by lazy {
        MutableLiveData<List<MallMerchant>>()
    }

    fun getOwnerMalls(shoppingMallRequestBody: ShoppingMallRequestBody) {
        if (checkNetworkStatus()) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(AppConstants.serverConnectionErrorMessage)
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(repository.getOwnerMalls(shoppingMallRequestBody))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        shoppingMallResponse.postValue(apiResponse.body.data?.marchents?.data)
                    }
                    is ApiEmptyResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.EMPTY)
                    }
                    is ApiErrorResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.ERROR)
                    }
                }
            }
        }
    }
}