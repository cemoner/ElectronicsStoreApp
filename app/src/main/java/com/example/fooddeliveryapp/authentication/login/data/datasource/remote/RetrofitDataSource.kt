package com.example.fooddeliveryapp.authentication.login.data.datasource.remote

import com.example.fooddeliveryapp.authentication.login.data.api.UserAPI
import com.example.fooddeliveryapp.authentication.login.data.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val userApi: UserAPI
) {
    suspend fun searchUser(store: String, userId: String): Response<UserResponse> {
        return userApi.getUser(store, userId)
    }
}