package com.example.fooddeliveryapp.features.cart.domain.repository

import com.example.fooddeliveryapp.common.domain.model.entity.Product

interface CartDataRepository {
    suspend fun getCart(store:String, userId:String): Result<List<Product>>
}