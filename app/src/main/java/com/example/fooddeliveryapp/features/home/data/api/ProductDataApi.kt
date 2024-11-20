package com.example.fooddeliveryapp.features.home.data.api

import com.example.fooddeliveryapp.features.home.data.model.response.ProductMultipleResponse
import com.example.fooddeliveryapp.features.home.data.model.response.ProductSingularResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductDataApi:API {

    @GET("get_products")
    suspend fun getProducts(@Header("store") store:String):Response<ProductMultipleResponse>

    @GET("get_product_detail")
    suspend fun getProductDetail(@Header("store") store:String, @Query("id") productId:Int):Response<ProductSingularResponse>

}