package com.example.fooddeliveryapp.profile.profile.data.datasource.remote.retrofit

import com.example.fooddeliveryapp.profile.profile.data.api.ProfileApi
import com.example.fooddeliveryapp.profile.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.profile.profile.data.model.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val profileApi: ProfileApi) {

    suspend fun getUser(userRequest: UserRequest): Response<UserResponse> {
        return profileApi.getUser(userRequest.store,userRequest.userId)}


}