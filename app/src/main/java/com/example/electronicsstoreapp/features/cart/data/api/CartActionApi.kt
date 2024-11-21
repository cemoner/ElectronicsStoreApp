package com.example.electronicsstoreapp.features.cart.data.api

import com.example.electronicsstoreapp.features.cart.data.model.request.ClearCartRequest
import com.example.electronicsstoreapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.electronicsstoreapp.features.cart.data.model.response.ClearCartResponse
import com.example.electronicsstoreapp.features.cart.data.model.response.DeleteFromCartResponse
import com.example.electronicsstoreapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CartActionApi:API {


    @POST("delete_from_cart")
    suspend fun deleteFromCart(@Header("store") store:String, @Body deleteFromCartRequest: DeleteFromCartRequest): Response<DeleteFromCartResponse>

    @POST("clear_cart")
    suspend fun clearCart(@Header("store") store:String, @Body clearCartRequest: ClearCartRequest): Response<ClearCartResponse>


}