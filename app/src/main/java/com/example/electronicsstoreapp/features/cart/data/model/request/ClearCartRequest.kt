package com.example.electronicsstoreapp.features.cart.data.model.request

import com.google.gson.annotations.SerializedName

data class ClearCartRequest(
    @SerializedName("userId")
    val userId: String,
)
