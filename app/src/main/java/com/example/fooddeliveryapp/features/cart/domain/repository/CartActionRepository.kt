package com.example.fooddeliveryapp.features.cart.domain.repository

interface CartActionRepository {

    suspend fun deleteFromCart(store: String, userId:String,productId:Int): Result<String>
    suspend fun clearCart(store:String,userId:String):Result<String>

}