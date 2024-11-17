package com.example.fooddeliveryapp.home.data.datasource.remote.retrofit

import com.example.fooddeliveryapp.home.data.api.HomeApi
import com.example.fooddeliveryapp.home.data.api.ProductDetailApi
import com.example.fooddeliveryapp.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.home.data.model.response.AddResponse
import com.example.fooddeliveryapp.home.data.model.response.ProductResponse
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(private val homeApi: HomeApi, private val productDetailApi: ProductDetailApi) {

    // Home Page Actions

    suspend fun getProducts(store:String): Response<ProductResponse> = homeApi.getProducts(store)

    suspend fun getSaleProducts(store:String): Response<ProductResponse>  = homeApi.getSaleProducts(store)

    suspend fun getProductsByCategory(store:String,category:String): Response<ProductResponse>  = homeApi.getProductsByCategory(store,category)

    suspend fun searchProduct(store:String,query:String): Response<ProductResponse>  = homeApi.searchProduct(store,query)


    // Detail Page Actions

    suspend fun addToCart(store:String,addRequest: AddRequest): Response<AddResponse> = productDetailApi.addToCart(store,addRequest)

    suspend fun addToFavorites(store:String,addRequest: AddRequest): Response<AddResponse> = productDetailApi.addToFavorites(store,addRequest)



}