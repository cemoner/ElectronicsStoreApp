package com.example.fooddeliveryapp.features.cart.data.api

import com.example.fooddeliveryapp.common.data.model.response.MultipleProductsResponse
import com.example.fooddeliveryapp.features.cart.data.model.request.ClearCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.response.ClearCartResponse
import com.example.fooddeliveryapp.features.cart.data.model.response.DeleteFromCartResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CartActionApi:API {


    @POST("delete_from_cart")
    suspend fun deleteFromCart(@Header("store") store:String, @Body deleteFromCartRequest: DeleteFromCartRequest): Response<DeleteFromCartResponse>

    @POST("clear_cart")
    suspend fun clearCart(@Header("store") store:String, @Body clearCartRequest: ClearCartRequest): Response<ClearCartResponse>


}