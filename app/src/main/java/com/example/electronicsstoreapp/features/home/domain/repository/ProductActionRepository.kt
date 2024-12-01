package com.example.electronicsstoreapp.features.home.domain.repository

import com.example.electronicsstoreapp.features.home.domain.model.ActionResult

interface ProductActionRepository {
    suspend fun addToCart(
        store: String,
        userId: String,
        productId: Int,
    ): Result<ActionResult>

    suspend fun addToFavorites(
        store: String,
        userId: String,
        productId: Int,
    ): Result<ActionResult>

    suspend fun deleteFromFavorites(
        store: String,
        userId: String,
        productId: Int,
    ): Result<ActionResult>
}
