package com.example.fooddeliveryapp.features.profile.profile.data.mapper

import com.example.fooddeliveryapp.features.profile.profile.data.model.entity.UserDto
import com.example.fooddeliveryapp.features.profile.profile.domain.model.UserInfo

fun UserDto.toDomainModel():UserInfo{
    return UserInfo(
        userId = userId,
        email = email,
        name = name,
        phone = phone
    )
}