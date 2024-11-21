package com.example.electronicsstoreapp.features.home.data.datasource.remote

import com.example.electronicsstoreapp.features.home.data.api.ProductActionApi
import com.example.electronicsstoreapp.features.home.data.model.request.ActionRequest
import com.example.electronicsstoreapp.features.home.data.model.request.ActionRequest2
import com.example.electronicsstoreapp.features.home.data.model.response.ActionResponse
import retrofit2.Response
import javax.inject.Inject

class ProductActionRemoteDataSource @Inject constructor(private val productActionApi: ProductActionApi) {

    suspend fun addToCart(store:String,addRequest: ActionRequest): Response<ActionResponse> = productActionApi.addToCart(store,addRequest)

    suspend fun addToFavorites(store:String,addRequest: ActionRequest): Response<ActionResponse> = productActionApi.addToFavorites(store,addRequest)

    suspend fun deleteFromFavorites(store:String,removeRequest: ActionRequest2): Response<ActionResponse> = productActionApi.deleteFromFavorites(store,removeRequest)
}