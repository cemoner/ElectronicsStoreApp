package com.example.electronicsstoreapp.features.profile.authentication.data.datasource.remote

import com.example.electronicsstoreapp.features.profile.authentication.data.api.AuthApi
import com.example.electronicsstoreapp.features.profile.authentication.data.model.request.SignInRequest
import com.example.electronicsstoreapp.features.profile.authentication.data.model.request.SignUpRequest
import com.example.electronicsstoreapp.features.profile.authentication.data.model.response.AuthResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource
    @Inject
    constructor(
        private val authApi: AuthApi,
    ) {
        suspend fun signIn(signInRequest: SignInRequest): Response<AuthResponse> = authApi.signIn(signInRequest)

        suspend fun signUp(signUpRequest: SignUpRequest): Response<AuthResponse> = authApi.signUp(signUpRequest)
    }
