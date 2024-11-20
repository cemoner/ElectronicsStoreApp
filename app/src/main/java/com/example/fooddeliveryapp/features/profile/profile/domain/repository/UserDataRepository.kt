package com.example.fooddeliveryapp.features.profile.profile.domain.repository

import com.example.fooddeliveryapp.features.profile.profile.domain.model.User

interface UserDataRepository {

    suspend fun getUser(storeName:String,userId:String): Result<User>
}