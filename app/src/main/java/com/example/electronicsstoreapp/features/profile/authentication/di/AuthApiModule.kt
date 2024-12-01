package com.example.electronicsstoreapp.features.profile.authentication.di

import com.example.electronicsstoreapp.features.profile.authentication.data.api.AuthApi
import com.example.electronicsstoreapp.retrofit.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthApiModule {
    @Provides
    @Singleton
    fun providesAuthApi(): AuthApi = ApiClient.create()
}