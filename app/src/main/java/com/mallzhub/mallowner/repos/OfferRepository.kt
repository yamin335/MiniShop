package com.mallzhub.mallowner.repos

import com.mallzhub.mallowner.api.ApiService
import com.mallzhub.mallowner.models.OfferProductListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfferRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getOfferList(): Response<OfferProductListResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getOfferList()
        }
    }
}