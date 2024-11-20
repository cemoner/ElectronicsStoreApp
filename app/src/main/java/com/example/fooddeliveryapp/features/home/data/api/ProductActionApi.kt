package com.example.fooddeliveryapp.features.home.data.api

import com.example.fooddeliveryapp.features.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.features.home.data.model.response.AddResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductActionApi:API {

    @POST("add_to_cart")
    suspend fun addToCart(
        @Header("store") store: String,
        @Body addRequest: AddRequest
    ): Response<AddResponse>

    @POST("add_to_favorites")
    suspend fun addToFavorites(
        @Header("store") store: String,
        @Body addRequest: AddRequest
    ): Response<AddResponse>

}