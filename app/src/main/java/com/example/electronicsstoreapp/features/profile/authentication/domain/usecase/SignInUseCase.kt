package com.example.electronicsstoreapp.features.profile.authentication.domain.usecase

import com.example.electronicsstoreapp.features.profile.authentication.domain.model.AuthDetail
import com.example.electronicsstoreapp.features.profile.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignInUseCase
    @Inject
    constructor(
        private val authenticationRepository: AuthenticationRepository,
    ) {
        suspend operator fun invoke(
            email: String,
            password: String,
        ): Result<AuthDetail> = authenticationRepository.signIn(email, password)
    }
