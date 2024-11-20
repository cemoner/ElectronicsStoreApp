package com.example.fooddeliveryapp.common.data.model.response

import com.example.fooddeliveryapp.common.data.model.entity.ProductDto
import com.google.gson.annotations.SerializedName

data class SingularProductResponse(
    @SerializedName("product")
    val products: ProductDto,
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)