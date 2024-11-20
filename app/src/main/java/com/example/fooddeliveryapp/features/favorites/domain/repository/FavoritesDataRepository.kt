package com.example.fooddeliveryapp.features.favorites.domain.repository

import com.example.fooddeliveryapp.common.domain.model.entity.Product

interface FavoritesDataRepository {
    suspend fun getFavorites(store:String,userId:String): Result<List<Product>>
}