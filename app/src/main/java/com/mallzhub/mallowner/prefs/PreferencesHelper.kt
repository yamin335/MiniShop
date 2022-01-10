package com.mallzhub.mallowner.prefs

import com.mallzhub.mallowner.api.ProfileInfo
import com.mallzhub.mallowner.api.TokenInformation
import com.mallzhub.mallowner.models.registration.MallOwner


interface PreferencesHelper {

    var test: String?

    var isRegistered: Boolean

    var isTermsAccepted: Boolean

    var pinNumber: String?

    var mobileNo: String?

    var operator: String?

    var deviceId: String?

    var deviceName: String?

    var deviceModel: String?

    var isLoggedIn: Boolean

    var accessToken: String?

    var refreshToken: String?

    var phoneNumber: String?

    var userId: Int

    var userRole: String?

    var accessTokenExpiresIn: Long

    val isAccessTokenExpired: Boolean

    fun saveMallOwner(mallOwner: MallOwner)

    fun getMallOwner(): MallOwner

    fun getAccessTokenHeader(): String

    fun getAuthHeader(token: String?): String

    fun logoutUser()

    fun saveToken(tokenInformation: TokenInformation)

    fun saveUserProfile(profile: ProfileInfo)

    fun getToken(): TokenInformation

    var validityLimiterMap: MutableMap<String, Long>?

    var language: String?
}
