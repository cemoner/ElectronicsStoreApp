package com.example.fooddeliveryapp.features.favorites.domain.repository


interface FavoritesActionRepository {
    suspend fun removeFromFavorites(store:String,userId:String,productId:Int): Result<String>

}