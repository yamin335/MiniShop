package com.mallzhub.mallowner.repos

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.mallzhub.mallowner.api.ApiService
import com.mallzhub.mallowner.models.order.OrderListResponse
import com.mallzhub.mallowner.models.order.OrderStoreBody
import com.mallzhub.shop.models.order.OrderStoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getOrderList(page: Int?, token: String?): Response<OrderListResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getOrderList(page, token)
        }
    }

    suspend fun placeOrder(orderStoreBody: OrderStoreBody?): Response<OrderStoreResponse> {
        val jsonString = Gson().toJson(orderStoreBody)
        val jsonObject = JsonParser().parse(jsonString).asJsonObject
        return withContext(Dispatchers.IO) {
            apiService.placeOrder(jsonObject)
        }
    }
}