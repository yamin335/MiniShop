package com.rtchubs.talika.models

import java.io.Serializable

data class AllShoppingMallResponse(val code: Int?, val status: String?, val message: String?, val data: List<ShoppingMall>?)

data class ShoppingMall(val id: Int?, val name: String?, val email: String?, val address: String?, val phone: String?,
                        val level: Int?, val thumbnail: String?, val offday: String?, val created_at: String?,
                        val updated_at: String?, val lat: Double?, val long: Double?, val username: String?,
                        val password: String?,  val levels: List<ShoppingMallLevel>?) : Serializable

data class ShoppingMallLevel(val id: Int?, val name: String?, val block_number: String?,
                             val shopping_mall_id: Int?, val created_at: String?, val updated_at: String?) : Serializable

data class ShoppingMallRequestBody(val token: String?, val type: String?)

data class ShoppingMallResponse(val code: Int?, val status: String?, val message: String?, val data: ShoppingMallResponseData?)

data class ShoppingMallResponseData(val marchents: MallMerchants?, val mall: ShoppingMall?) : Serializable

data class MallMerchant(val id: Int?, val name: String?, val user_name: String?, val shop_name: String?, val mobile: String?,
                         val lat: Double?, val long: Double?, val whatsApp: String?, val email: String?, val address: String?,
                         val shop_address: String?, val shop_logo: String?, val thumbnail: String?, val isActive: Int?,
                         val shopping_mall_id: Int?, val shopping_mall_level_id: Int?, val rent_date: String?,
                         val monthly_rent: Int?, val advance_pament: Int?, val advance_payment_date: String?,
                         val agreement_duration: Int?, val created_at: String?, val updated_at: String?, val type: String?,
                         val offer_discount_type: Any?, val offer_discount_percent: Any?, val offer_banner: Any?,
                         val offer_valid_from: Any?, val offer_valid_to: Any?, val branch_id: Int?, val shopping_mall: ShoppingMall?) : Serializable

data class ShoppingMallAllMerchantResponse(val code: Int?, val status: String?, val message: String?, val data: MallMerchants?)

data class ShoppingMallResponseLinks(val url: String?, val label: Any?, val active: Boolean?) : Serializable

data class MallMerchants(val current_page: Int?, val data: List<MallMerchant>?, val first_page_url: String?,
                         val from: Int?, val last_page: Int?, val last_page_url: String?,
                         val links: List<ShoppingMallResponseLinks>?, val next_page_url: Any?, val path: String?,
                         val per_page: Int?, val prev_page_url: Any?, val to: Int?, val total: Int?) : Serializable

data class ShopUpdateResponse(val lat: String?, val long: String?, val shopping_mall_level_id: String?, val shopping_mall_id: String?, val user_name: String?)
