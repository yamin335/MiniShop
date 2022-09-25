package com.rtchubs.talika.ui.wallet

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rtchubs.talika.R
import com.rtchubs.talika.api.*
import com.rtchubs.talika.local_db.dao.CartDao
import com.rtchubs.talika.models.GiftPointStoreBody
import com.rtchubs.talika.models.GiftPointStoreResponseData
import com.rtchubs.talika.models.PaymentMethod
import com.rtchubs.talika.repos.GiftPointRepository
import com.rtchubs.talika.ui.common.BaseViewModel
import com.rtchubs.talika.util.AppConstants
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