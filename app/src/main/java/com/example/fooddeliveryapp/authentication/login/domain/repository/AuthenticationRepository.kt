package com.example.fooddeliveryapp.authentication.login.domain.repository

import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.authentication.login.data.model.result.NetworkResult
import com.example.fooddeliveryapp.authentication.login.data.model.entity.User

interface AuthenticationRepository {
    suspend fun signIn(signInRequest: SignInRequest): NetworkResult<User>
    suspend fun signUp(signUpRequest: SignUpRequest): NetworkResult<User>
}