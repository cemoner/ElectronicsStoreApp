package com.example.fooddeliveryapp.profile.profile.domain.repository

import com.example.fooddeliveryapp.profile.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.profile.profile.domain.model.UserInfo

interface ProfileRepository {
    suspend fun getUser(userRequest: UserRequest): UserInfo
}