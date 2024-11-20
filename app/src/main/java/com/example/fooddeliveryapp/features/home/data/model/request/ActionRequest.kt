package com.example.fooddeliveryapp.features.home.data.model.request

import com.google.gson.annotations.SerializedName

data class ActionRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("productId")
    val productId: Int,

)