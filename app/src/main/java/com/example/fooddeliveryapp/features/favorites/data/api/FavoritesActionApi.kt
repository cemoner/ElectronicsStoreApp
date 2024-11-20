package com.example.fooddeliveryapp.features.favorites.data.api

import com.example.fooddeliveryapp.features.favorites.data.model.request.FavoritesActionRequest
import com.example.fooddeliveryapp.features.favorites.data.model.response.FavoritesActionResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FavoritesActionApi:API {
    @POST("remove_from_favorites")
    suspend fun removeFromFavorites(@Header("store") store:String, @Body favoritesActionRequest: FavoritesActionRequest): Response<FavoritesActionResponse>

}