package com.example.fooddeliveryapp.features.home.data.datasource.remote

import com.example.fooddeliveryapp.features.home.data.api.ProductDataApi
import com.example.fooddeliveryapp.features.home.data.model.response.ProductMultipleResponse
import com.example.fooddeliveryapp.features.home.data.model.response.ProductSingularResponse
import retrofit2.Response
import javax.inject.Inject

class ProductDataRemoteDataSource @Inject constructor(private val productDataApi: ProductDataApi) {

    suspend fun getProducts(store:String): Response<ProductMultipleResponse> = productDataApi.getProducts(store)

    suspend fun getProductDetail(store:String, productId:Int): Response<ProductSingularResponse> = productDataApi.getProductDetail(store, productId)

}