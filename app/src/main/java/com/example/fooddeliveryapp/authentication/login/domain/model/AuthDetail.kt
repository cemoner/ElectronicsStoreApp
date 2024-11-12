package com.example.fooddeliveryapp.authentication.login.domain.model

import com.google.gson.annotations.SerializedName

data class AuthDetail(
    val userId: String?,
    val status:Int,
    val message:String
)
