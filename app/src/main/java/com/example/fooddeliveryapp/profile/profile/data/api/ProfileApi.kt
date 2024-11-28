package com.example.fooddeliveryapp.profile.profile.data.api

import com.example.fooddeliveryapp.retrofit.API
import com.example.fooddeliveryapp.profile.profile.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProfileApi:API {

    @GET("get_user")
    suspend fun getUser(
        @Header("store") store: String,
        @Query("userId") userId: String
    ): Response<UserResponse>

}