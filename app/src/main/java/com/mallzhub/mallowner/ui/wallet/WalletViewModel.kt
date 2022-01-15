package com.mallzhub.mallowner.ui.wallet

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.api.*
import com.mallzhub.mallowner.local_db.dao.CartDao
import com.mallzhub.mallowner.models.GiftPointStoreBody
import com.mallzhub.mallowner.models.GiftPointStoreResponseData
import com.mallzhub.mallowner.models.PaymentMethod
import com.mallzhub.mallowner.repos.GiftPointRepository
import com.mallzhub.mallowner.ui.common.BaseViewModel
import com.mallzhub.mallowner.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WalletViewModel @Inject constructor(
    private val application: Application,
    private val giftPointRepository: GiftPointRepository,
    private val cartDao: CartDao
) : BaseViewModel(application) {

    val cartItemCount: LiveData<Int> = liveData {
        cartDao.getCartItemsCount().collect { count ->
            emit(count)
        }
    }

    val paymentMethodList: List<PaymentMethod>
        get() = listOf(
            /* PaymentMethod(
                 "0",
                 "\u2022\u2022\u2022\u2022 4122",R.drawable.maestro

             ),
             PaymentMethod(
                 "1",
                 "\u2022\u2022\u2022\u2022 9120",R.drawable.visa
             ),*/
            PaymentMethod(
                "-1",
                "Add Payment Method", R.drawable.plus
            )
        )

    val giftPointStoreResponse: MutableLiveData<GiftPointStoreResponseData> by lazy {
        MutableLiveData<GiftPointStoreResponseData>()
    }

    fun saveGiftPoints(giftPointStoreBody: GiftPointStoreBody) {
        if (checkNetworkStatus()) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(AppConstants.serverConnectionErrorMessage)
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(giftPointRepository.saveGiftPoints(giftPointStoreBody))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        giftPointStoreResponse.postValue(apiResponse.body.data)
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

//    val slideDataList = listOf(
//        SlideData(R.drawable.slider_image_1, "Ads1", "Easy, Fast and Secure Way"),
//        SlideData(R.drawable.slider_image_1, "Ads2", "Easy, Fast and Secure Way"),
//        SlideData(R.drawable.slider_image_1, "Ads3", "Easy, Fast and Secure Way"),
//        SlideData(R.drawable.slider_image_1, "Ads4", "Easy, Fast and Secure Way"),
//        SlideData(R.drawable.slider_image_1, "Ads5", "Easy, Fast and Secure Way")
//    )
//
//    inner class SlideData(
//        var slideImage: Int,
//        var textTitle: String,
//        var descText: String
//    )

}