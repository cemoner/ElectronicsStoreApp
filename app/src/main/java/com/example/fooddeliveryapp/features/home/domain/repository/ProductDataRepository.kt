package com.example.fooddeliveryapp.features.home.domain.repository

import com.example.fooddeliveryapp.common.domain.model.Product

interface ProductDataRepository {
    suspend fun getProducts(store:String): Result<List<Product>>

    suspend fun getProductDetail(store: String,productId:Int): Result<Product>

    suspend fun getFavorites(store:String,userId:String): Result<List<Product>>

}