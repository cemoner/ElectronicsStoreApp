package com.example.fooddeliveryapp.features.home.data.datasource.remote

import com.example.fooddeliveryapp.features.home.data.api.ProductDataApi
import com.example.fooddeliveryapp.common.data.model.response.MultipleProductsResponse
import com.example.fooddeliveryapp.common.data.model.response.SingularProductResponse
import retrofit2.Response
import javax.inject.Inject

class ProductDataRemoteDataSource @Inject constructor(private val productDataApi: ProductDataApi) {

    suspend fun getProducts(store:String): Response<MultipleProductsResponse> = productDataApi.getProducts(store)

    suspend fun getProductDetail(store:String, productId:Int): Response<SingularProductResponse> = productDataApi.getProductDetail(store, productId)

    suspend fun getFavorites(store:String, userId:String): Response<MultipleProductsResponse> = productDataApi.getFavorites(store, userId)


}