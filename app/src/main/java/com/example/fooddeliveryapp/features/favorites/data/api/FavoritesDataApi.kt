package com.example.fooddeliveryapp.features.favorites.data.api

import com.example.fooddeliveryapp.common.data.model.response.MultipleProductsResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FavoritesDataApi:API {

    @GET("get_favorites")
    suspend fun getFavorites(@Header("store") store:String,@Query("userId") userId:String ): Response<MultipleProductsResponse>

}