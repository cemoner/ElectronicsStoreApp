package com.example.fooddeliveryapp.features.profile.authentication.login.domain.usecase

import com.example.fooddeliveryapp.features.profile.authentication.login.domain.model.AuthDetail
import com.example.fooddeliveryapp.features.profile.authentication.login.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend fun signIn(email:String,password:String): Result<AuthDetail> = authenticationRepository.signIn(email,password)

    suspend fun signUp(email:String,password:String,name:String,phone:String,address:String): Result<AuthDetail> = authenticationRepository.signUp(email,password,name,phone,address)

}