package com.example.fooddeliveryapp.features.profile.profile.data.model.response

import com.example.fooddeliveryapp.features.profile.profile.data.model.entity.UserDto
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    val user: UserDto?,
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)

