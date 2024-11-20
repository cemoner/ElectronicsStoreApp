package com.example.fooddeliveryapp.features.cart.data.model.response

import com.example.fooddeliveryapp.common.data.model.entity.ProductDto
import com.google.gson.annotations.SerializedName

data class GetCartResponse(
    @SerializedName("products")
    val products:List<ProductDto>,
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)
