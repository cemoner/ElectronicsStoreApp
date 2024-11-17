package com.example.fooddeliveryapp.profile.authentication.login.data.repository

import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import com.example.fooddeliveryapp.profile.authentication.login.data.datasource.remote.retrofit.RetrofitDataSource
import com.example.fooddeliveryapp.profile.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.profile.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.profile.authentication.login.domain.model.AuthDetail
import com.example.fooddeliveryapp.profile.authentication.login.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val remoteDataSource: RetrofitDataSource):
    AuthenticationRepository, ApiHandler
{
    override suspend fun signIn(signInRequest: SignInRequest): AuthDetail {
        val result = handleApi { remoteDataSource.signIn(signInRequest) }
        when(result){
            is NetworkResult.Success -> {
                return AuthDetail(result.data.userId,result.data.status,result.data.message)
            }
            is NetworkResult.Error -> {
                return if(result.errorMsg != null){
                    AuthDetail("-1",result.code,result.errorMsg)
                }
                else {
                    AuthDetail("-1",result.code,"Something went wrong")
                }
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): AuthDetail {
        val result = handleApi { remoteDataSource.signUp(signUpRequest) }
        when(result){
            is NetworkResult.Success -> {
                return AuthDetail(result.data.userId,result.data.status,result.data.message)
            }
            is NetworkResult.Error -> {
                return if(result.errorMsg != null){
                    AuthDetail("-1",result.code,result.errorMsg)
                }
                else {
                    AuthDetail("-1",result.code,"Something went wrong")
                }
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }
}

