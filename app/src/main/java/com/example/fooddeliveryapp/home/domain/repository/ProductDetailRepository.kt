package com.example.fooddeliveryapp.home.domain.repository

import com.example.fooddeliveryapp.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.home.domain.model.AddResult

interface ProductDetailRepository {
    suspend fun addToCart(store:String,addRequest:AddRequest):AddResult
    suspend fun addToFavorites(store:String,addRequest:AddRequest):AddResult
}