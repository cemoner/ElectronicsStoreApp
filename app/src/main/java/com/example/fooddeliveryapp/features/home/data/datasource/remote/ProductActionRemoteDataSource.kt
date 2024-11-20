package com.example.fooddeliveryapp.features.home.data.datasource.remote

import com.example.fooddeliveryapp.features.home.data.api.ProductActionApi
import com.example.fooddeliveryapp.features.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.features.home.data.model.response.AddResponse
import retrofit2.Response
import javax.inject.Inject

class ProductActionRemoteDataSource @Inject constructor(private val productActionApi: ProductActionApi) {

    suspend fun addToCart(store:String,addRequest: AddRequest): Response<AddResponse> = productActionApi.addToCart(store,addRequest)

    suspend fun addToFavorites(store:String,addRequest: AddRequest): Response<AddResponse> = productActionApi.addToFavorites(store,addRequest)
}