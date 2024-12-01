package com.example.electronicsstoreapp.features.profile.authentication.data.api

import com.example.electronicsstoreapp.features.profile.authentication.data.model.request.SignInRequest
import com.example.electronicsstoreapp.features.profile.authentication.data.model.request.SignUpRequest
import com.example.electronicsstoreapp.features.profile.authentication.data.model.response.AuthResponse
import com.example.electronicsstoreapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi : API {
    @POST("sign_in")
    suspend fun signIn(
        @Body signInRequest: SignInRequest,
    ): Response<AuthResponse>

    @POST("sign_up")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): Response<AuthResponse>
}
