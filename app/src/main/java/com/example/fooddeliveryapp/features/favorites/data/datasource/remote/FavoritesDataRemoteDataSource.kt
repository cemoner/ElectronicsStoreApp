package com.example.fooddeliveryapp.features.favorites.data.datasource.remote

import com.example.fooddeliveryapp.features.favorites.data.api.FavoritesDataApi
import javax.inject.Inject

class FavoritesDataRemoteDataSource @Inject constructor(private val favoritesDataApi: FavoritesDataApi) {

    suspend fun getFavorites(store:String,userId:String) = favoritesDataApi.getFavorites(store,userId)
}