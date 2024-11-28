package com.example.fooddeliveryapp.home.data.model.request

import com.google.gson.annotations.SerializedName

data class AddRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("productId")
    val productId: String,

)