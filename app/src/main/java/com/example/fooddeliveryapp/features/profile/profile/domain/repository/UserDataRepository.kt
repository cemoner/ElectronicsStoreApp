package com.example.fooddeliveryapp.features.profile.profile.domain.repository

import com.example.fooddeliveryapp.features.profile.profile.domain.model.UserInfo

interface UserDataRepository {

    suspend fun getUser(storeName:String,userId:String): Result<UserInfo>
}