package com.example.fooddeliveryapp.features.favorites.data.model.response

import com.google.gson.annotations.SerializedName

data class FavoritesActionResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)
