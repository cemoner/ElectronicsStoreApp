package com.example.electronicsstoreapp.features.profile.profile.domain.usecase

import com.example.electronicsstoreapp.features.profile.profile.domain.model.User
import com.example.electronicsstoreapp.features.profile.profile.domain.repository.UserDataRepository
import javax.inject.Inject

class GetUserUseCase
    @Inject
    constructor(
        private val profileRepository: UserDataRepository,
    ) {
        suspend operator fun invoke(
            storeName: String,
            userId: String,
        ): Result<User> = profileRepository.getUser(storeName, userId)
    }
