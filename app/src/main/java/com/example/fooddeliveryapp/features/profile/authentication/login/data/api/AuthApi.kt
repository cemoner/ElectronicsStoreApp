package com.example.fooddeliveryapp.features.profile.authentication.login.data.api

import com.example.fooddeliveryapp.features.profile.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.features.profile.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.features.profile.authentication.login.data.model.response.AuthResponse
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi:API {

    @POST("sign_in")
    suspend fun signIn(@Body signInRequest: SignInRequest): Response<AuthResponse>

    @POST("sign_up")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<AuthResponse>
}
