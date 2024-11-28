package com.example.fooddeliveryapp.home.domain.repository

import com.example.fooddeliveryapp.home.domain.model.Product

interface HomeRepository {
    suspend fun getProducts(store:String): List<Product>

    suspend fun getSaleProducts(store:String): List<Product>

    suspend fun getProductsByCategory(store:String,category:String): List<Product>

    suspend fun searchProduct(store:String,query:String): List<Product>
}