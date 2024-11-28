package com.example.fooddeliveryapp.features.home.data.repository

import com.example.fooddeliveryapp.features.home.data.datasource.remote.ProductActionRemoteDataSource
import com.example.fooddeliveryapp.features.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.features.home.domain.exception.CartOperationException
import com.example.fooddeliveryapp.features.home.domain.exception.FavoritesOperationException
import com.example.fooddeliveryapp.features.home.domain.model.AddResult
import com.example.fooddeliveryapp.features.home.domain.repository.ProductActionRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult


open class ProductActionRepositoryImpl(private val productActionDataSource: ProductActionRemoteDataSource):ProductActionRepository,ApiHandler {

     override suspend fun addToCart(store: String,userId:String,productId:Int): Result<AddResult> {
         val result = handleApi { productActionDataSource.addToCart(store, AddRequest(userId,productId)) }
         when (result) {
             is NetworkResult.Success -> {
                 return Result.success(AddResult(
                     message = result.data.message
                 ))
             }
             is NetworkResult.Error -> {
                 return Result.failure(CartOperationException(result.errorMsg!!))
             }
             is NetworkResult.Exception -> {
                 return Result.failure(result.e)
             }
         }
     }

     override suspend fun addToFavorites(store: String,userId:String,productId:Int): Result<AddResult> {
         val result = handleApi { productActionDataSource.addToFavorites(store, AddRequest(userId,productId)) }
         when (result) {
             is NetworkResult.Success -> {
                 return Result.success(AddResult(
                     message = result.data.message
                 ))
             }
             is NetworkResult.Error -> {
                 return Result.failure(FavoritesOperationException(result.errorMsg!!))
             }
             is NetworkResult.Exception -> {
                 return Result.failure(result.e)
             }
         }
     }

}