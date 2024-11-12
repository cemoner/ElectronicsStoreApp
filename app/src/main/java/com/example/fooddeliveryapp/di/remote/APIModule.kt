package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.authentication.login.data.api.AuthApi
import com.example.fooddeliveryapp.retrofit.ApiClient
import com.example.fooddeliveryapp.shared.profile.data.api.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

class APIModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi = ApiClient.create()

    @Provides
    @Singleton
    fun provideProfileApi(): ProfileApi = ApiClient.create()

}
