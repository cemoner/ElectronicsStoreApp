package com.example.fooddeliveryapp.authentication.login.domain.usecase

import com.example.fooddeliveryapp.authentication.login.data.model.entity.User
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.authentication.login.data.model.response.AuthResponse
import com.example.fooddeliveryapp.authentication.login.data.model.result.NetworkResult
import com.example.fooddeliveryapp.authentication.login.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend fun signIn(signInRequest: SignInRequest):AuthResponse
    {
        val result =  authenticationRepository.signIn(signInRequest)
        when(result){
            is NetworkResult.Success -> {
                return AuthResponse(result.data.userId,result.data.status,result.data.message)
            }
            is NetworkResult.Error -> {
                AuthResponse(null,400,"Network Error")
            }
            is NetworkResult.Exception -> {
                AuthResponse(null,400,"Exception Error")
                throw result.e
            }
        }
        return AuthResponse(null,400,"Unknown Error")
    }

    suspend fun signUp(signUpRequest: SignUpRequest):AuthResponse {
        val result =  authenticationRepository.signUp(signUpRequest)
        when(result){
            is NetworkResult.Success -> {
                return AuthResponse(result.data.userId,result.data.status,result.data.message)
            }
            is NetworkResult.Error -> {
                AuthResponse(null,400,"Network Error")
            }
            is NetworkResult.Exception -> {
                AuthResponse(null,400,"Exception Error")
                throw result.e
            }
        }
        return AuthResponse(null,400,"Unknown Error")
    }

}