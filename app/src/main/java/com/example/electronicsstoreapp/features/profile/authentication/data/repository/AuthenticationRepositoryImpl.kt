package com.example.electronicsstoreapp.features.profile.authentication.data.repository

import com.example.electronicsstoreapp.features.profile.authentication.data.datasource.remote.AuthRemoteDataSource
import com.example.electronicsstoreapp.features.profile.authentication.data.model.request.SignInRequest
import com.example.electronicsstoreapp.features.profile.authentication.data.model.request.SignUpRequest
import com.example.electronicsstoreapp.features.profile.authentication.domain.exception.UserNotFoundException
import com.example.electronicsstoreapp.features.profile.authentication.domain.model.AuthDetail
import com.example.electronicsstoreapp.features.profile.authentication.domain.repository.AuthenticationRepository
import com.example.electronicsstoreapp.retrofit.ApiHandler
import com.example.electronicsstoreapp.retrofit.NetworkResult
import javax.inject.Inject

class AuthenticationRepositoryImpl
    @Inject
    constructor(
        private val authDataSource: AuthRemoteDataSource,
    ) : AuthenticationRepository,
        ApiHandler {
        override suspend fun signIn(
            email: String,
            password: String,
        ): Result<AuthDetail> {
            val result = handleApi { authDataSource.signIn(SignInRequest(email, password)) }
            when (result) {
                is NetworkResult.Success -> {
                    val userId = result.data.userId
                    return if (userId == null) {
                        Result.failure(UserNotFoundException(result.data.message))
                    } else {
                        Result.success(
                            AuthDetail(
                                result.data.userId,
                                result.data.message,
                            ),
                        )
                    }
                }
                is NetworkResult.Error -> {
                    return Result.failure(Exception(result.errorMsg))
                }
                is NetworkResult.Exception -> {
                    return Result.failure(result.e)
                }
            }
        }

        override suspend fun signUp(
            email: String,
            password: String,
            name: String,
            phone: String,
            address: String,
        ): Result<AuthDetail> {
            val result = handleApi { authDataSource.signUp(SignUpRequest(email, password, name, phone, address)) }
            when (result) {
                is NetworkResult.Success -> {
                    val userId = result.data.userId
                    return if (userId == null) {
                        Result.failure(UserNotFoundException(result.data.message))
                    } else {
                        Result.success(
                            AuthDetail(
                                result.data.userId,
                                result.data.message,
                            ),
                        )
                    }
                }
                is NetworkResult.Error -> {
                    return Result.failure(Exception(result.errorMsg))
                }
                is NetworkResult.Exception -> {
                    return Result.failure(result.e)
                }
            }
        }
    }
