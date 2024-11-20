package com.example.fooddeliveryapp.features.home.data.repository

import com.example.fooddeliveryapp.features.home.data.datasource.remote.ProductDataRemoteDataSource
import com.example.fooddeliveryapp.common.data.mapper.toDomainModel
import com.example.fooddeliveryapp.common.domain.model.Product
import com.example.fooddeliveryapp.features.home.domain.repository.ProductDataRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class ProductDataRepositoryImpl @Inject constructor( private val productDataRemoteDataSource:
ProductDataRemoteDataSource): ProductDataRepository, ApiHandler {

    override suspend fun getProducts(store: String): Result<List<Product>> {
        val result = handleApi { productDataRemoteDataSource.getProducts(store) }
        when (result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.products.map { it.toDomainModel() })
            }

            is NetworkResult.Error -> {
                return Result.failure(Exception(result.errorMsg))
            }
            is NetworkResult.Exception -> {
                return Result.failure(result.e)
            }
        }
    }

    override suspend fun getProductDetail(store: String,productId:Int): Result<Product> {
        val result = handleApi { productDataRemoteDataSource.getProductDetail(store,productId) }
        when (result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.products.toDomainModel())
            }

            is NetworkResult.Error -> {
                return Result.failure(Exception(result.errorMsg))
            }
            is NetworkResult.Exception -> {
                return Result.failure(result.e)
            }
        }
    }
    override suspend fun getFavorites(store: String, userId: String): Result<List<Product>> {
        val result = handleApi { productDataRemoteDataSource.getFavorites(store,userId)}
        when(result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.products.map { it.toDomainModel() })
            }
            is NetworkResult.Error -> return Result.failure(Exception(result.errorMsg))

            is NetworkResult.Exception -> return Result.failure(result.e)

        }
    }
}