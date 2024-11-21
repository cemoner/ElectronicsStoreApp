package com.example.electronicsstoreapp.features.home.data.model.response

import com.google.gson.annotations.SerializedName

data class ActionResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
)