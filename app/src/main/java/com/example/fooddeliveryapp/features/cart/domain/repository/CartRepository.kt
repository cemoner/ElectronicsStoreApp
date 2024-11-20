package com.example.fooddeliveryapp.features.cart.domain.repository

import com.example.fooddeliveryapp.common.domain.model.entity.Product

interface CartRepository {

    suspend fun getCart(store:String, userId:String): Result<List<Product>>
    suspend fun deleteFromCart(store: String, userId:String,productId:Int): Result<String>
    suspend fun clearCart(store:String,userId:String):Result<String>

}