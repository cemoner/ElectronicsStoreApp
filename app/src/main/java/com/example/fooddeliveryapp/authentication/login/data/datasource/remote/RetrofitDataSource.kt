package com.example.fooddeliveryapp.authentication.login.data.datasource.remote

import com.example.fooddeliveryapp.authentication.login.data.api.AuthAPI
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.authentication.login.data.model.entity.User
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val authApi: AuthAPI
) {

    suspend fun signIn(signInRequest: SignInRequest):Response<User> = authApi.signIn(signInRequest)

    suspend fun signUp(signUpRequest: SignUpRequest):Response<User> = authApi.signUp(signUpRequest)
}