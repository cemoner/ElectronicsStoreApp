package com.example.electronicsstoreapp.features.home.data.repository

import com.example.electronicsstoreapp.features.home.data.datasource.remote.ProductActionRemoteDataSource
import com.example.electronicsstoreapp.features.home.data.model.request.ActionRequest
import com.example.electronicsstoreapp.features.home.data.model.request.ActionRequest2
import com.example.electronicsstoreapp.features.home.domain.exception.CartOperationException
import com.example.electronicsstoreapp.features.home.domain.exception.FavoritesOperationException
import com.example.electronicsstoreapp.features.home.domain.model.ActionResult
import com.example.electronicsstoreapp.features.home.domain.repository.ProductActionRepository
import com.example.electronicsstoreapp.retrofit.ApiHandler
import com.example.electronicsstoreapp.retrofit.NetworkResult

open class ProductActionRepositoryImpl(
    private val productActionDataSource: ProductActionRemoteDataSource,
) : ProductActionRepository,
    ApiHandler {
    override suspend fun addToCart(
        store: String,
        userId: String,
        productId: Int,
    ): Result<ActionResult> {
        val result = handleApi { productActionDataSource.addToCart(store, ActionRequest(userId, productId)) }
        return when (result) {
            is NetworkResult.Success -> {
                Result.success(
                    ActionResult(
                        message = result.data.message,
                    ),
                )
            }

            is NetworkResult.Error -> {
                Result.failure(CartOperationException(result.errorMsg!!))
            }

            is NetworkResult.Exception -> {
                Result.failure(result.e)
            }
        }
    }

    override suspend fun addToFavorites(
        store: String,
        userId: String,
        productId: Int,
    ): Result<ActionResult> {
        val result = handleApi { productActionDataSource.addToFavorites(store, ActionRequest(userId, productId)) }
        return when (result) {
            is NetworkResult.Success -> {
                Result.success(
                    ActionResult(
                        message = result.data.message,
                    ),
                )
            }

            is NetworkResult.Error -> {
                Result.failure(FavoritesOperationException(result.errorMsg!!))
            }

            is NetworkResult.Exception -> {
                Result.failure(result.e)
            }
        }
    }

    override suspend fun deleteFromFavorites(
        store: String,
        userId: String,
        productId: Int,
    ): Result<ActionResult> {
        val result = handleApi { productActionDataSource.deleteFromFavorites(store, ActionRequest2(userId, productId)) }
        return when (result) {
            is NetworkResult.Success -> {
                Result.success(
                    ActionResult(
                        message = result.data.message,
                    ),
                )
            }

            is NetworkResult.Error -> {
                Result.failure(FavoritesOperationException(result.errorMsg!!))
            }

            is NetworkResult.Exception -> {
                Result.failure(result.e)
            }
        }
    }
}
