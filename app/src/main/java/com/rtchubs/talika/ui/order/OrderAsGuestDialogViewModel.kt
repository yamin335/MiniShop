package com.rtchubs.talika.ui.order

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rtchubs.talika.api.*
import com.rtchubs.talika.local_db.dao.CartDao
import com.rtchubs.talika.repos.OrderRepository
import com.rtchubs.talika.ui.common.BaseViewModel
import com.rtchubs.talika.util.AppConstants
import com.rtchubs.talika.models.order.OrderStoreBody
import com.rtchubs.talika.models.order.OrderStoreProduct
import com.rtchubs.shop.models.order.OrderStoreResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject

class OrderAsGuestDialogViewModel @Inject constructor(
    private val application: Application,
    private val cartDao: CartDao,
    private val orderRepository: OrderRepository
) : BaseViewModel(application) {

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mobile: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val address: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val userNote: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val invoiceNumber: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val orderPlaceResponse: MutableLiveData<OrderStoreResponse> by lazy {
        MutableLiveData<OrderStoreResponse>()
    }

    val orderItems: MutableLiveData<List<OrderStoreProduct>> by lazy {
        MutableLiveData<List<OrderStoreProduct>>()
    }

    fun deleteAllCartItems() {
        val handler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
        }

        viewModelScope.launch(handler) {
            cartDao.deleteAllCartItems()
        }
    }

    fun deleteCartItemsByIds(itemIds: List<Int>) {
        val handler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
        }

        viewModelScope.launch(handler) {
            cartDao.deleteCartItemsByIds(itemIds)
        }
    }

    fun placeOrder(orderStoreBody: OrderStoreBody) {
        if (checkNetworkStatus()) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(AppConstants.serverConnectionErrorMessage)
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(orderRepository.placeOrder(orderStoreBody))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        orderPlaceResponse.postValue(apiResponse.body)
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

    fun generateInvoiceID(): String {
        val random1 = "${1 + SecureRandom().nextInt(9999999)}"
        val random2 = "${1 + SecureRandom().nextInt(999999)}"
        invoiceNumber.postValue("IV-${random1}${random2}")
        return "IV-${random1}${random2}"
    }
}