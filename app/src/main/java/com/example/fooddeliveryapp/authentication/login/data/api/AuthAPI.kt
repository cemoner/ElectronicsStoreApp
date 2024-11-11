package com.example.fooddeliveryapp.authentication.login.data.api

import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.authentication.login.data.model.entity.User
import com.example.fooddeliveryapp.retrofit.API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI:API {

    @POST("sign_in")
    suspend fun signIn(@Body signInRequest: SignInRequest): Response<User>

    @POST("sign_up")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<User>
}
