package com.example.fooddeliveryapp.features.favorites.data.api

import com.example.fooddeliveryapp.features.home.data.model.response.ProductMultipleResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FavoritesApi:API {

    @GET("get_favorites")
    suspend fun getFavorites(@Header("user") user:String): Response<ProductMultipleResponse>

}