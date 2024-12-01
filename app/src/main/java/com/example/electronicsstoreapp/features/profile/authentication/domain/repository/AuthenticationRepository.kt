package com.example.electronicsstoreapp.features.profile.authentication.domain.repository

import com.example.electronicsstoreapp.features.profile.authentication.domain.model.AuthDetail

interface AuthenticationRepository {
    suspend fun signIn(
        email: String,
        password: String,
    ): Result<AuthDetail>

    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        phone: String,
        address: String,
    ): Result<AuthDetail>
}
