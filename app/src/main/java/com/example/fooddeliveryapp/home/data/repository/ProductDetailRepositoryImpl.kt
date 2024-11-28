package com.example.fooddeliveryapp.home.data.repository

import com.example.fooddeliveryapp.home.data.datasource.remote.retrofit.RetrofitDataSource
import com.example.fooddeliveryapp.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.home.domain.model.AddResult
import com.example.fooddeliveryapp.home.domain.repository.ProductDetailRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(private val remoteDataSource: RetrofitDataSource): ProductDetailRepository,ApiHandler {

    override suspend fun addToCart(store: String, addRequest: AddRequest): AddResult {
        val result = handleApi { remoteDataSource.addToCart(store, addRequest) }
        when (result) {
            is NetworkResult.Success -> {
                return AddResult(
                    status = result.data.status,
                    message = result.data.message
                )
            }

            is NetworkResult.Error -> {
                return AddResult(
                    status = result.code,
                    message = result.errorMsg
                )
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }

    override suspend fun addToFavorites(store: String, addRequest: AddRequest): AddResult {
        val result = handleApi { remoteDataSource.addToCart(store, addRequest) }
        when (result) {
            is NetworkResult.Success -> {
                return AddResult(
                    status = result.data.status,
                    message = result.data.message
                )
            }

            is NetworkResult.Error -> {
                return AddResult(
                    status = result.code,
                    message = result.errorMsg
                )
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }
}