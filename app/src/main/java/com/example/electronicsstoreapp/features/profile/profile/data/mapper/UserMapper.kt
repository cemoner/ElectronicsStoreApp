package com.example.electronicsstoreapp.features.profile.profile.data.mapper

import com.example.electronicsstoreapp.features.profile.profile.data.model.entity.UserDto
import com.example.electronicsstoreapp.features.profile.profile.domain.model.User

fun UserDto.toDomainModel(): User {
    return User(
        userId = userId,
        email = email,
        name = name,
        phone = phone
    )
}