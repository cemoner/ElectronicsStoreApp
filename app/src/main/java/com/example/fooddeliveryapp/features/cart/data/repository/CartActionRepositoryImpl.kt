package com.example.fooddeliveryapp.features.cart.data.repository

import com.example.fooddeliveryapp.common.data.mapper.toDomainModel
import com.example.fooddeliveryapp.common.domain.model.entity.Product
import com.example.fooddeliveryapp.features.cart.data.datasource.remote.CartActionDataSource
import com.example.fooddeliveryapp.features.cart.data.model.request.ClearCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.GetCartRequest
import com.example.fooddeliveryapp.features.cart.domain.repository.CartRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class CartActionRepositoryImpl @Inject constructor(private val retrofitDataSource: CartActionDataSource):
    CartRepository,ApiHandler {
    override suspend fun getCart(store: String, userId:String): Result<List<Product>> {
        val result = handleApi { retrofitDataSource.getCart(store, GetCartRequest(userId)) }

        when (result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.products.map { it.toDomainModel() })
            }
            is NetworkResult.Error -> return Result.failure(Exception(result.errorMsg))
            is NetworkResult.Exception -> return Result.failure(result.e)
        }
    }

    override suspend fun deleteFromCart(store: String, userId:String, productId:Int): Result<String> {
        val result = handleApi { retrofitDataSource.deleteFromCart(store, DeleteFromCartRequest(userId,productId)
        ) }

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

    override suspend fun clearCart(store: String, userId: String): Result<String> {
        val result = handleApi { retrofitDataSource.clearCart(store, ClearCartRequest(userId))}

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