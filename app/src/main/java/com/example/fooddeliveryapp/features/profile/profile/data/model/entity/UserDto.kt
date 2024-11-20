package com.example.fooddeliveryapp.features.profile.profile.data.model.entity

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String
)