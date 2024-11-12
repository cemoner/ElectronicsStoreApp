package com.example.fooddeliveryapp.shared.profile.domain.repository

import com.example.fooddeliveryapp.shared.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.shared.profile.domain.model.UserInfo

interface ProfileRepository {
    suspend fun getUser(userRequest: UserRequest): UserInfo
}