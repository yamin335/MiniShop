package com.rtchubs.talika.repos

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.rtchubs.talika.api.ApiService
import com.rtchubs.talika.models.*
import com.rtchubs.talika.models.payment_account_models.AddCardOrBankResponse
import com.rtchubs.talika.models.payment_account_models.BankOrCardListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getOwnerMalls(shoppingMallRequestBody: ShoppingMallRequestBody): Response<ShoppingMallResponse> {
        val jsonString = Gson().toJson(shoppingMallRequestBody)
        val jsonObject = JsonParser().parse(jsonString).asJsonObject
        return withContext(Dispatchers.IO) {
            apiService.getOwnerMalls(jsonObject)
        }
    }

    suspend fun getOwnerMallAllMerchants(shoppingMallRequestBody: ShoppingMallRequestBody): Response<ShoppingMallAllMerchantResponse> {
        val jsonString = Gson().toJson(shoppingMallRequestBody)
        val jsonObject = JsonParser().parse(jsonString).asJsonObject
        return withContext(Dispatchers.IO) {
            apiService.getOwnerMallAllMerchants(jsonObject)
        }
    }

    suspend fun requestBankListRepo(type:String,token:String): Response<BankOrCardListResponse> {
        return withContext(Dispatchers.IO) {
            apiService.requestBankList(type,token)
        }
    }

    suspend fun addBankRepo(bankId: Int, accountNumber: String, token: String): Response<AddCardOrBankResponse> {
        val jsonObjectBody = JsonObject().apply {
            addProperty("bankId", bankId)
            addProperty("accountNumber", accountNumber)
        }

        return withContext(Dispatchers.IO) {
            apiService.addBankAccount(jsonObjectBody, token)
        }
    }

    suspend fun addCardRepo(bankId: Int, cardNumber: String, expireMonth: Int, expireYear: Int, token: String): Response<AddCardOrBankResponse> {
        val jsonObjectBody = JsonObject().apply {
            addProperty("bankId", bankId)
            addProperty("cardNumber", cardNumber)
            addProperty("expireMonth", expireMonth)
            addProperty("expireYear", expireYear)
        }

        return withContext(Dispatchers.IO) {
            apiService.addCardAccount(jsonObjectBody, token)
        }
    }

    // eDokanPat
    suspend fun getAllMallsRepo(): Response<AllShoppingMallResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getAllMalls()
        }
    }

    suspend fun getAllMerchantsRepo(): Response<AllMerchantResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getAllMerchants()
        }
    }

    suspend fun getAllProductsRepo(id: Int?): Response<AllProductResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getAllProducts(id)
        }
    }

    suspend fun getProductDetailsRepo(id: Int?): Response<ProductDetailsResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getProductDetails(id)
        }
    }

    suspend fun updateShop(merchantId: Int?, shop_name: String, user_name: String, lat: String, long: String, mallLevelId: String, mallId: String): Response<ShopUpdateResponse> {
        return withContext(Dispatchers.IO) {
            apiService.updateShop(
                merchantId,
                shop_name,
                user_name,
                lat,
                long,
                mallLevelId,
                mallId)
        }
    }

//    suspend fun updateShop(merchantId: Int?, shop_name: String, user_name: String, lat: String, long: String, mallLevelId: String, mallId: String): Response<ShopUpdateResponse> {
//        return withContext(Dispatchers.IO) {
//            apiService.updateShop(
//                merchantId,
//                user_name.toRequestBody("text/plain".toMediaTypeOrNull()),
//                lat.toRequestBody("text/plain".toMediaTypeOrNull()),
//                long.toRequestBody("text/plain".toMediaTypeOrNull()),
//                mallLevelId.toRequestBody("text/plain".toMediaTypeOrNull()),
//                mallId.toRequestBody("text/plain".toMediaTypeOrNull()))
//        }
//    }
}