package com.example.fooddeliveryapp.shared.profile.data.datasource.remote

import android.util.Log
import com.example.fooddeliveryapp.shared.profile.data.api.ProfileApi
import com.example.fooddeliveryapp.shared.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.shared.profile.data.model.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val profileApi: ProfileApi) {

    suspend fun getUser(userRequest: UserRequest): Response<UserResponse> {
        Log.d("RetrofitDataSource", "getUser: $userRequest")
        return profileApi.getUser(userRequest.store,userRequest.userId)}


}