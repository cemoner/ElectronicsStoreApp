package com.example.electronicsstoreapp.features.profile.profile.data.datasource.remote

import com.example.electronicsstoreapp.features.profile.profile.data.api.UserApi
import com.example.electronicsstoreapp.features.profile.profile.data.model.request.UserRequest
import com.example.electronicsstoreapp.features.profile.profile.data.model.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val profileApi: UserApi
) {

    suspend fun getUser(userRequest: UserRequest): Response<UserResponse> {
        return profileApi.getUser(userRequest.store,userRequest.userId)}


}