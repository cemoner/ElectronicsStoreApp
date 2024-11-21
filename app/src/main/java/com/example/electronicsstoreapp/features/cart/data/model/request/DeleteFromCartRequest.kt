package com.example.electronicsstoreapp.features.cart.data.model.request

import com.google.gson.annotations.SerializedName

data class DeleteFromCartRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("id")
    val productId: Int
)
