package com.rtchubs.talika.models.registration

data class LoginRequestBody(val email: String?, val password: String?, val userType: String?, val url: String?, val usertype: String?)

data class LoginResponse(val success: Boolean?, val response: MallOwner?)

data class MallOwner(val id: Int?, val name: String?, val email: String?,
                             val address: String?, val phone: String?, val level: Int?,
                             val thumbnail: String?, val offday: String?, val created_at: String?,
                             val updated_at: String?, val lat: Double?, val long: Double?,
                             val username: String?, val password: String?, val token: String?)