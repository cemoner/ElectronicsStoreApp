package com.example.electronicsstoreapp.features.profile.profile.data.api

import com.example.electronicsstoreapp.features.profile.profile.data.model.response.UserResponse
import com.example.electronicsstoreapp.retrofit.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserApi : API {
    @GET("get_user")
    suspend fun getUser(
        @Header("store") store: String,
        @Query("userId") userId: String,
    ): Response<UserResponse>
}
