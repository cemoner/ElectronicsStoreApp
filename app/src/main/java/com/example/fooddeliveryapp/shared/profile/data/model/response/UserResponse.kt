package com.example.fooddeliveryapp.shared.profile.data.model.response

import com.example.fooddeliveryapp.shared.profile.data.model.entity.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    val user: User?,  // Nested User object
    @SerializedName("status")
    val status: Int?,
    @SerializedName("message")
    val message: String?
)

