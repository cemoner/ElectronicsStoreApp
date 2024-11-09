package com.example.fooddeliveryapp.authentication.login.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("profile_picture") val profilePicture: String,
    // Add any other fields as needed
)
