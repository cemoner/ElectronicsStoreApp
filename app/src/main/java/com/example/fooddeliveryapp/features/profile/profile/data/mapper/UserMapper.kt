package com.example.fooddeliveryapp.features.profile.profile.data.mapper

import com.example.fooddeliveryapp.features.profile.profile.data.model.entity.UserDto
import com.example.fooddeliveryapp.features.profile.profile.domain.model.entity.User

fun UserDto.toDomainModel(): User {
    return User(
        userId = userId,
        email = email,
        name = name,
        phone = phone
    )
}