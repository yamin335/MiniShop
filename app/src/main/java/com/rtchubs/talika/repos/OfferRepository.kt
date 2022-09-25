package com.rtchubs.talika.repos

import com.rtchubs.talika.api.ApiService
import com.rtchubs.talika.models.OfferProductListResponse
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