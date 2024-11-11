package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.authentication.login.data.api.AuthAPI
import com.example.fooddeliveryapp.authentication.login.data.datasource.remote.RetrofitDataSource
import com.example.fooddeliveryapp.authentication.login.data.repository.AuthenticationRepositoryImpl
import com.example.fooddeliveryapp.authentication.login.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthenticationRepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(remoteDataSource: RetrofitDataSource): AuthenticationRepository =  AuthenticationRepositoryImpl(remoteDataSource)
}