package com.example.fooddeliveryapp.authentication.login.data.api

import com.example.fooddeliveryapp.authentication.login.data.model.UserResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserAPI:API {
    @GET("users/search")
    suspend fun getUser(
        @Header("store") store: String,
        @Query("user_id") userId: String
    ): Response<UserResponse>
}