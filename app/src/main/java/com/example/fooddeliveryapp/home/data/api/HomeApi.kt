package com.example.fooddeliveryapp.home.data.api

import com.example.fooddeliveryapp.home.data.model.response.ProductResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApi:API {

    @GET("get_products")
    suspend fun getProducts(@Header("store") store:String):Response<ProductResponse>

    @GET("get_sale_products")
    suspend fun getSaleProducts(@Header("store") store:String):Response<ProductResponse>

    @GET("get_products_by_category")
    suspend fun getProductsByCategory(@Header("store") store:String, @Query("category") category:String):Response<ProductResponse>

    @GET("search_product")
    suspend fun searchProduct(@Header("store") store:String, @Query("query") query:String):Response<ProductResponse>

}