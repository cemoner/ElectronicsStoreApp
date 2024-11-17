package com.example.fooddeliveryapp.profile.profile.data.repository

import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import com.example.fooddeliveryapp.profile.profile.data.datasource.remote.retrofit.RetrofitDataSource
import com.example.fooddeliveryapp.profile.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.profile.profile.domain.model.UserInfo
import com.example.fooddeliveryapp.profile.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val remoteDataSource: RetrofitDataSource):ProfileRepository,ApiHandler {
    override suspend fun getUser(userRequest: UserRequest): UserInfo {
        val result = handleApi { remoteDataSource.getUser(userRequest) }
        when(result){
            is NetworkResult.Success -> {
                result.data
                return UserInfo(
                    userId = result.data.user?.userId,
                    email = result.data.user?.email,
                    name = result.data.user?.name,
                    phone = result.data.user?.phone,
                )
            }
            is NetworkResult.Error -> {
                return UserInfo("-1",null,null,null)
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }
}