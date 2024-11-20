package com.example.fooddeliveryapp.features.cart.data.model.response

import com.example.fooddeliveryapp.features.cart.domain.model.Product
import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("products")
    val products:List<Product>,
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)
