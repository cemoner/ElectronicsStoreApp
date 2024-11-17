package com.example.fooddeliveryapp.home.data.api

import com.example.fooddeliveryapp.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.home.data.model.response.AddResponse
import com.example.fooddeliveryapp.retrofit.API
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductDetailApi:API {

    @POST
    suspend fun addToCart(
        @Header("store") store: String,
        @Body addRequest: AddRequest
    ): Response<AddResponse>

    @POST
    suspend fun addToFavorites(
        @Header("store") store: String,
        @Body addRequest: AddRequest
    ): Response<AddResponse>
}