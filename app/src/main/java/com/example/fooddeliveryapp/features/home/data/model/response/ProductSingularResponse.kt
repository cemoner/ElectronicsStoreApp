package com.example.fooddeliveryapp.features.home.data.model.response

import com.example.fooddeliveryapp.common.data.model.entity.ProductDto
import com.google.gson.annotations.SerializedName

data class ProductSingularResponse(
    @SerializedName("product")
    val products: ProductDto,
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)