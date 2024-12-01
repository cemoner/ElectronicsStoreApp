package com.example.electronicsstoreapp.features.home.data.repository

import com.example.electronicsstoreapp.common.data.mapper.toDomainModel
import com.example.electronicsstoreapp.common.domain.model.Product
import com.example.electronicsstoreapp.features.home.data.datasource.remote.ProductDataRemoteDataSource
import com.example.electronicsstoreapp.features.home.domain.repository.ProductDataRepository
import com.example.electronicsstoreapp.retrofit.ApiHandler
import com.example.electronicsstoreapp.retrofit.NetworkResult
import javax.inject.Inject

class ProductDataRepositoryImpl
    @Inject
    constructor(
        private val productDataRemoteDataSource: ProductDataRemoteDataSource,
    ) : ProductDataRepository,
        ApiHandler {
        override suspend fun getProducts(store: String): Result<List<Product>> {
            val result = handleApi { productDataRemoteDataSource.getProducts(store) }
            return when (result) {
                is NetworkResult.Success -> {
                    Result.success(result.data.products.map { it.toDomainModel() })
                }

                is NetworkResult.Error -> {
                    Result.failure(Exception(result.errorMsg))
                }

                is NetworkResult.Exception -> {
                    Result.failure(result.e)
                }
            }
        }

        override suspend fun getProductDetail(
            store: String,
            productId: Int,
        ): Result<Product> {
            val result = handleApi { productDataRemoteDataSource.getProductDetail(store, productId) }
            return when (result) {
                is NetworkResult.Success -> {
                    Result.success(result.data.products.toDomainModel())
                }

                is NetworkResult.Error -> {
                    Result.failure(Exception(result.errorMsg))
                }

                is NetworkResult.Exception -> {
                    Result.failure(result.e)
                }
            }
        }

        override suspend fun getFavorites(
            store: String,
            userId: String,
        ): Result<List<Product>> {
            val result = handleApi { productDataRemoteDataSource.getFavorites(store, userId) }
            return when (result) {
                is NetworkResult.Success -> {
                    Result.success(result.data.products.map { it.toDomainModel() })
                }

                is NetworkResult.Error -> Result.failure(Exception(result.errorMsg))

                is NetworkResult.Exception -> Result.failure(result.e)
            }
        }

    override suspend fun searchProducts(store: String, query: String): Result<List<Product>> {
        val result = handleApi { productDataRemoteDataSource.searchProduct(store,query) }
        return when(result){
            is NetworkResult.Success -> {
                Result.success(result.data.products.map { it.toDomainModel() })
            }

            is NetworkResult.Error -> {
                Result.failure(Exception(result.errorMsg))
            }

            is NetworkResult.Exception -> {
                Result.failure(result.e)
            }
        }
    }
}
