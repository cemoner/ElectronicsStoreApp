package com.example.fooddeliveryapp.features.home.data.api

import com.example.fooddeliveryapp.common.data.model.response.MultipleProductsResponse
import com.example.fooddeliveryapp.common.data.model.response.SingularProductResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductDataApi:API {

    @GET("get_products")
    suspend fun getProducts(@Header("store") store:String):Response<MultipleProductsResponse>

    @GET("get_product_detail")
    suspend fun getProductDetail(@Header("store") store:String, @Query("id") productId:Int):Response<SingularProductResponse>

}