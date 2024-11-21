package com.example.electronicsstoreapp.features.home.data.api

import com.example.electronicsstoreapp.common.data.model.response.MultipleProductsResponse
import com.example.electronicsstoreapp.common.data.model.response.SingularProductResponse
import com.example.electronicsstoreapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductDataApi:API {

    @GET("get_products")
    suspend fun getProducts(@Header("store") store:String):Response<MultipleProductsResponse>

    @GET("get_product_detail")
    suspend fun getProductDetail(@Header("store") store:String, @Query("id") productId:Int):Response<SingularProductResponse>

    @GET("get_favorites")
    suspend fun getFavorites(@Header("store") store:String, @Query("userId") userId:String):Response<MultipleProductsResponse>

}