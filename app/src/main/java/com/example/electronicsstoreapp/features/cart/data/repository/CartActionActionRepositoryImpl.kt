package com.example.electronicsstoreapp.features.cart.data.repository

import com.example.electronicsstoreapp.features.cart.data.datasource.remote.CartActionRemoteDataSource
import com.example.electronicsstoreapp.features.cart.data.model.request.ClearCartRequest
import com.example.electronicsstoreapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.electronicsstoreapp.features.cart.domain.repository.CartActionRepository
import com.example.electronicsstoreapp.retrofit.ApiHandler
import com.example.electronicsstoreapp.retrofit.NetworkResult
import javax.inject.Inject

class CartActionActionRepositoryImpl
    @Inject
    constructor(
        private val retrofitDataSource: CartActionRemoteDataSource,
    ) : CartActionRepository,
        ApiHandler {
        override suspend fun deleteFromCart(
            store: String,
            userId: String,
            productId: Int,
        ): Result<String> {
            val result = handleApi { retrofitDataSource.deleteFromCart(store, DeleteFromCartRequest(userId, productId)) }

            when (result) {
                is NetworkResult.Success -> {
                    return Result.success(result.data.message)
                }
                is NetworkResult.Error -> return Result.failure(Exception(result.errorMsg))

                is NetworkResult.Exception -> {
                    return Result.failure(result.e)
                }
            }
        }

        override suspend fun clearCart(
            store: String,
            userId: String,
        ): Result<String> {
            val result = handleApi { retrofitDataSource.clearCart(store, ClearCartRequest(userId)) }

            when (result) {
                is NetworkResult.Success -> {
                    return Result.success(result.data.message)
                }

                is NetworkResult.Error -> return Result.failure(Exception(result.errorMsg))

                is NetworkResult.Exception -> {
                    return Result.failure(result.e)
                }
            }
        }
    }
