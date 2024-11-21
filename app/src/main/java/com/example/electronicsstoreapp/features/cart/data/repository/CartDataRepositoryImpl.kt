package com.example.electronicsstoreapp.features.cart.data.repository

import com.example.electronicsstoreapp.common.data.mapper.toDomainModel
import com.example.electronicsstoreapp.common.domain.model.Product
import com.example.electronicsstoreapp.features.cart.data.datasource.remote.CartDataRemoteDataSource
import com.example.electronicsstoreapp.features.cart.data.model.request.GetCartRequest
import com.example.electronicsstoreapp.features.cart.domain.repository.CartDataRepository
import com.example.electronicsstoreapp.retrofit.ApiHandler
import com.example.electronicsstoreapp.retrofit.NetworkResult
import javax.inject.Inject

class CartDataRepositoryImpl @Inject constructor(private val cartDataRemoteDataSource: CartDataRemoteDataSource):CartDataRepository,ApiHandler{

    override suspend fun getCart(store: String, userId:String): Result<List<Product>> {
        val result = handleApi { cartDataRemoteDataSource.getCart(store, GetCartRequest(userId)) }

        when (result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.products.map { it.toDomainModel() })
            }
            is NetworkResult.Error -> return Result.failure(Exception(result.errorMsg))
            is NetworkResult.Exception -> return Result.failure(result.e)
        }
    }
}

