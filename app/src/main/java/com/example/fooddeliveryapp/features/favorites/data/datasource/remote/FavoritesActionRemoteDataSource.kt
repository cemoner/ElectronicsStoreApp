package com.example.fooddeliveryapp.features.favorites.data.datasource.remote

import com.example.fooddeliveryapp.features.favorites.data.api.FavoritesActionApi
import com.example.fooddeliveryapp.features.favorites.data.model.request.FavoritesActionRequest
import com.example.fooddeliveryapp.features.favorites.data.model.response.FavoritesActionResponse
import retrofit2.Response
import javax.inject.Inject

class FavoritesActionRemoteDataSource @Inject constructor(private val favoritesActionApi: FavoritesActionApi) {

    suspend fun removeFromFavorites(store:String,favoritesActionRequest: FavoritesActionRequest):Response<FavoritesActionResponse>
    = favoritesActionApi.removeFromFavorites(store,favoritesActionRequest)

}