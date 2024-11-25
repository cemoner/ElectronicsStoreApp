package com.example.electronicsstoreapp.features.cart.data.api

import com.example.electronicsstoreapp.common.data.model.response.MultipleProductsResponse
import com.example.electronicsstoreapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CartDataApi : API {
    @GET("get_cart_products")
    suspend fun getCart(
        @Header("store") store: String,
        @Query("userId") userId: String,
    ): Response<MultipleProductsResponse>
}
