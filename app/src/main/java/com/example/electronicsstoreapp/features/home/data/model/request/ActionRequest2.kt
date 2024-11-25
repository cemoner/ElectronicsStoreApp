package com.example.electronicsstoreapp.features.home.data.model.request

import com.google.gson.annotations.SerializedName

data class ActionRequest2(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("id")
    val productId: Int,
)
