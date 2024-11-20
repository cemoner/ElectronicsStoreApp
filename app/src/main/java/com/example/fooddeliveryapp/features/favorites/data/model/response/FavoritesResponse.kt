package com.example.fooddeliveryapp.features.favorites.data.model.response
import com.example.fooddeliveryapp.features.favorites.domain.model.Product
import com.google.gson.annotations.SerializedName

data class FavoritesResponse(
    @SerializedName("products")
    val products:List<Product>,
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)