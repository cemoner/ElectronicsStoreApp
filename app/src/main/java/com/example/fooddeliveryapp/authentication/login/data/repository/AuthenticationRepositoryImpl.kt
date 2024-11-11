package com.example.fooddeliveryapp.authentication.login.data.repository

import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.authentication.login.data.datasource.remote.RetrofitDataSource
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.retrofit.NetworkResult
import com.example.fooddeliveryapp.authentication.login.data.model.entity.User
import com.example.fooddeliveryapp.authentication.login.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val remoteDataSource: RetrofitDataSource):
    AuthenticationRepository, ApiHandler
{
    override suspend fun signIn(signInRequest: SignInRequest): NetworkResult<User> {
        return handleApi { remoteDataSource.signIn(signInRequest) }
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): NetworkResult<User> {
        return handleApi { remoteDataSource.signUp(signUpRequest) }
    }

}

