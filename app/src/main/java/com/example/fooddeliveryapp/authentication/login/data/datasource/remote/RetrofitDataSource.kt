package com.example.fooddeliveryapp.authentication.login.data.datasource.remote

import com.example.fooddeliveryapp.authentication.login.data.api.AuthApi
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.authentication.login.data.model.response.AuthResponse
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun signIn(signInRequest: SignInRequest):Response<AuthResponse> = authApi.signIn(signInRequest)

    suspend fun signUp(signUpRequest: SignUpRequest):Response<AuthResponse> = authApi.signUp(signUpRequest)
}