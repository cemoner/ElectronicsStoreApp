package com.example.fooddeliveryapp.authentication.login.domain.repository

import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.authentication.login.domain.model.AuthDetail

interface AuthenticationRepository {
    suspend fun signIn(signInRequest: SignInRequest): AuthDetail
    suspend fun signUp(signUpRequest: SignUpRequest): AuthDetail
}