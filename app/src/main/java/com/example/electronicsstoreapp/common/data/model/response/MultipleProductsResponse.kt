package com.example.electronicsstoreapp.common.data.model.response

import com.example.electronicsstoreapp.common.data.model.entity.ProductDto
import com.google.gson.annotations.SerializedName

data class MultipleProductsResponse(
    @SerializedName("products")
    val products: List<ProductDto>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
)
