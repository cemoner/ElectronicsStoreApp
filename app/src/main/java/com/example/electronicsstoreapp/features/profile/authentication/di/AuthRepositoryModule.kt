package com.example.electronicsstoreapp.features.profile.authentication.di

import com.example.electronicsstoreapp.features.profile.authentication.data.datasource.remote.AuthRemoteDataSource
import com.example.electronicsstoreapp.features.profile.authentication.data.repository.AuthenticationRepositoryImpl
import com.example.electronicsstoreapp.features.profile.authentication.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AuthRepositoryModule {

    @Provides
    fun providesAuthenticationRepository(authRemoteDataSource: AuthRemoteDataSource): AuthenticationRepository =  AuthenticationRepositoryImpl(authRemoteDataSource)

}