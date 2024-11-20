package com.example.fooddeliveryapp.features.cart.domain.repository

import com.example.fooddeliveryapp.features.cart.domain.model.Product

interface CartRepository {

    suspend fun getCart(store:String, userId:String): List<Product>
    suspend fun deleteFromCart(store: String, userId:String,productId:Int): Pair<Int,String>
    suspend fun clearCart(store:String,userId:String):Int

}