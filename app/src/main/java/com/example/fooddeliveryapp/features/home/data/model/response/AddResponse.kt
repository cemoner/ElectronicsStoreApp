package com.example.fooddeliveryapp.features.home.data.model.response

import com.google.gson.annotations.SerializedName

data class AddResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)