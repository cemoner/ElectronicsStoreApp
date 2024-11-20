package com.example.fooddeliveryapp.features.favorites.data.repository

import com.example.fooddeliveryapp.features.favorites.data.datasource.remote.FavoritesActionRemoteDataSource
import com.example.fooddeliveryapp.features.favorites.data.model.request.FavoritesActionRequest
import com.example.fooddeliveryapp.features.favorites.domain.repository.FavoritesActionRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class FavoritesActionRepositoryImpl @Inject constructor(private val favoritesActionRemoteDataSource: FavoritesActionRemoteDataSource):FavoritesActionRepository,ApiHandler {
    override suspend fun removeFromFavorites(store: String, userId: String, productId: Int): Result<String> {
        val result = handleApi{favoritesActionRemoteDataSource.removeFromFavorites(store, FavoritesActionRequest(userId,productId))}
        when(result) {
            is NetworkResult.Success -> {
                return Result.success(result.data.message)
            }
            is NetworkResult.Error -> {
                return Result.failure(Exception(result.errorMsg))
            }
            is NetworkResult.Exception -> return Result.failure(result.e)

        }
    }
}