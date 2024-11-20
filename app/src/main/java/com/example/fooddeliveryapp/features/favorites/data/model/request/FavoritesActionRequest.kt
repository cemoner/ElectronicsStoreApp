package com.example.fooddeliveryapp.features.favorites.data.model.request

import com.google.gson.annotations.SerializedName

data class FavoritesActionRequest (
    @SerializedName("userId")
    val userId:String,
    @SerializedName("id")
    val productId:Int
)