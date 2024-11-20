package com.example.fooddeliveryapp.features.home.data.api

import com.example.fooddeliveryapp.features.home.data.model.request.ActionRequest
import com.example.fooddeliveryapp.features.home.data.model.response.ActionResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductActionApi:API {

    @POST("add_to_cart")
    suspend fun addToCart(
        @Header("store") store: String,
        @Body addRequest: ActionRequest
    ): Response<ActionResponse>

    @POST("add_to_favorites")
    suspend fun addToFavorites(
        @Header("store") store: String,
        @Body addRequest: ActionRequest
    ): Response<ActionResponse>


    @POST("delete_from_favorites")
    suspend fun deleteFromFavorites(
        @Header("store") store: String,
        @Body removeRequest: ActionRequest
    ): Response<ActionResponse>
}