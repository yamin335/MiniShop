package com.mallzhub.mallowner.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mallzhub.mallowner.api.*
import com.mallzhub.mallowner.models.registration.InquiryResponse
import com.mallzhub.mallowner.models.registration.LoginRequestBody
import com.mallzhub.mallowner.models.registration.LoginResponse
import com.mallzhub.mallowner.repos.LoginRepository
import com.mallzhub.mallowner.repos.RegistrationRepository
import com.mallzhub.mallowner.ui.common.BaseViewModel
import com.mallzhub.mallowner.util.AppConstants.serverConnectionErrorMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val application: Application,
    private val repository: LoginRepository) : BaseViewModel(application) {

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun signIn(loginRequestBody: LoginRequestBody): LiveData<LoginResponse> {
        val response: MutableLiveData<LoginResponse> = MutableLiveData()
        if (checkNetworkStatus()) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(serverConnectionErrorMessage)
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(repository.signIn(loginRequestBody))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        response.postValue(apiResponse.body)
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

        return response
    }
}