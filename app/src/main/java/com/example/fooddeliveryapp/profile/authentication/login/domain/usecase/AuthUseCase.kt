package com.example.fooddeliveryapp.profile.authentication.login.domain.usecase

import com.example.fooddeliveryapp.profile.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.profile.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.profile.authentication.login.domain.model.AuthDetail
import com.example.fooddeliveryapp.profile.authentication.login.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend fun signIn(signInRequest: SignInRequest): AuthDetail = authenticationRepository.signIn(signInRequest)

    suspend fun signUp(signUpRequest: SignUpRequest):AuthDetail = authenticationRepository.signUp(signUpRequest)

}