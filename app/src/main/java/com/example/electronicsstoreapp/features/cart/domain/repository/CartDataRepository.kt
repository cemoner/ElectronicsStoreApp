package com.example.electronicsstoreapp.features.cart.domain.repository

import com.example.electronicsstoreapp.common.domain.model.Product

interface CartDataRepository {
    suspend fun getCart(
        store: String,
        userId: String,
    ): Result<List<Product>>
}
