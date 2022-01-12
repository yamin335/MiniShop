package com.mallzhub.mallowner.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mallzhub.mallowner.api.*
import com.mallzhub.mallowner.local_db.dao.CartDao
import com.mallzhub.mallowner.models.AllMerchantResponse
import com.mallzhub.mallowner.models.ShopUpdateResponse
import com.mallzhub.mallowner.models.ShoppingMallLevel
import com.mallzhub.mallowner.models.registration.InquiryResponse
import com.mallzhub.mallowner.repos.HomeRepository
import com.mallzhub.mallowner.ui.common.BaseViewModel
import com.mallzhub.mallowner.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopEditViewModel @Inject constructor(
    private val application: Application,
    private val repository: HomeRepository,
    private val cartDao: CartDao
) : BaseViewModel(application) {

    var selectedServiceType = ""
    var selectedMallName = ""
    var selectedLevel: ShoppingMallLevel? = null

    val serviceName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val latitude: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val longitude: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val cartItemCount: LiveData<Int> = liveData {
        cartDao.getCartItemsCount().collect { count ->
            emit(count)
        }
    }
    val allShopListResponse: MutableLiveData<AllMerchantResponse> by lazy {
        MutableLiveData<AllMerchantResponse>()
    }

    fun getAllShopList() {
        if (checkNetworkStatus()) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(AppConstants.serverConnectionErrorMessage)
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(repository.getAllMerchantsRepo())) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        allShopListResponse.postValue(apiResponse.body)
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

    fun updateShop(merchantId: Int?, shop_name: String, user_name: String, lat: String, long: String, mallLevelId: String, mallId: String): LiveData<ShopUpdateResponse?> {
        val updateResponse = MutableLiveData<ShopUpdateResponse?>()
        if (checkNetworkStatus()) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(AppConstants.serverConnectionErrorMessage)
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(repository.updateShop(merchantId, shop_name, user_name, lat, long, mallLevelId, mallId))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        updateResponse.postValue(apiResponse.body)
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
        return updateResponse
    }
}