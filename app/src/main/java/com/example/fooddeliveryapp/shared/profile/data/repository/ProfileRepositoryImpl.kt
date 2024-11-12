package com.example.fooddeliveryapp.shared.profile.data.repository

import android.util.Log
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import com.example.fooddeliveryapp.shared.profile.data.datasource.remote.RetrofitDataSource
import com.example.fooddeliveryapp.shared.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.shared.profile.domain.model.UserInfo
import com.example.fooddeliveryapp.shared.profile.domain.repository.ProfileRepository
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
                return if(result.errorMsg != null){
                    return UserInfo(
                        userId = null,
                        email = null,
                        name = null,
                        phone = null,
                    )
                }
                else {
                    UserInfo(null,null,null,null)
                }
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }
}