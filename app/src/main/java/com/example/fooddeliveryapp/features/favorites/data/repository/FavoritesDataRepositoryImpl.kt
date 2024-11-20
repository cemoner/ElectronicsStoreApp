package com.example.fooddeliveryapp.features.favorites.data.repository

import com.example.fooddeliveryapp.common.data.mapper.toDomainModel
import com.example.fooddeliveryapp.common.domain.model.entity.Product
import com.example.fooddeliveryapp.features.favorites.data.datasource.remote.FavoritesDataRemoteDataSource
import com.example.fooddeliveryapp.features.favorites.domain.repository.FavoritesDataRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class FavoritesDataRepositoryImpl @Inject constructor(private val favoritesDataRemoteDataSource: FavoritesDataRemoteDataSource): FavoritesDataRepository ,ApiHandler{
    override suspend fun getFavorites(store: String, userId: String): Result<List<Product>> {
        val result = handleApi { favoritesDataRemoteDataSource.getFavorites(store,userId)}
        when(result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.products.map { it.toDomainModel() })
            }
            is NetworkResult.Error -> return Result.failure(Exception(result.errorMsg))

            is NetworkResult.Exception -> return Result.failure(result.e)

        }

    }
}