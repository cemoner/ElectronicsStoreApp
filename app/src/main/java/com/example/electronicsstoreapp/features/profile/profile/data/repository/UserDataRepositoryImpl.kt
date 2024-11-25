package com.example.electronicsstoreapp.features.profile.profile.data.repository

import com.example.electronicsstoreapp.features.profile.profile.data.datasource.remote.UserDataSource
import com.example.electronicsstoreapp.features.profile.profile.data.mapper.toDomainModel
import com.example.electronicsstoreapp.features.profile.profile.data.model.request.UserRequest
import com.example.electronicsstoreapp.features.profile.profile.domain.model.User
import com.example.electronicsstoreapp.features.profile.profile.domain.repository.UserDataRepository
import com.example.electronicsstoreapp.retrofit.ApiHandler
import com.example.electronicsstoreapp.retrofit.NetworkResult
import javax.inject.Inject

class UserDataRepositoryImpl
    @Inject
    constructor(
        private val userDataSource: UserDataSource,
    ) : UserDataRepository,
        ApiHandler {
        override suspend fun getUser(
            storeName: String,
            userId: String,
        ): Result<User> {
            val result = handleApi { userDataSource.getUser(UserRequest(storeName, userId)) }
            when (result) {
                is NetworkResult.Success -> {
                    result.data.user?.let { user ->
                        return Result.success(
                            user.toDomainModel(),
                        )
                    } ?: return Result.failure(IllegalStateException("User data is missing in success response"))
                }
                is NetworkResult.Error -> {
                    return Result.failure(NullPointerException("Null Pointer Exception"))
                }
                is NetworkResult.Exception -> {
                    return Result.failure(result.e)
                }
            }
        }
    }
