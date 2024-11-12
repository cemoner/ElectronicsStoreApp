package com.example.fooddeliveryapp.authentication.login.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)
