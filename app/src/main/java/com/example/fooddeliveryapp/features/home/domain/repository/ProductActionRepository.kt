package com.example.fooddeliveryapp.features.home.domain.repository

import com.example.fooddeliveryapp.features.home.domain.model.AddResult

interface ProductActionRepository {
    suspend fun addToCart(store:String,userId:String,productId:Int): Result<AddResult>

    suspend fun addToFavorites(store:String,userId:String,productId:Int): Result<AddResult>

}