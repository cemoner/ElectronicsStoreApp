package com.example.electronicsstoreapp.features.profile.authentication.domain.usecase

import com.example.electronicsstoreapp.features.profile.authentication.domain.model.AuthDetail
import com.example.electronicsstoreapp.features.profile.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignUpUseCase
    @Inject
    constructor(
        private val authenticationRepository: AuthenticationRepository,
    ) {
        suspend operator fun invoke(
            email: String,
            password: String,
            name: String,
            phone: String,
            address: String,
        ): Result<AuthDetail> = authenticationRepository.signUp(email, password, name, phone, address)
    }
