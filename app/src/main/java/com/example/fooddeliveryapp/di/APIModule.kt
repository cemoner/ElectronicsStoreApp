package com.example.fooddeliveryapp.di

import com.example.fooddeliveryapp.retrofit.ApiClient
import com.example.fooddeliveryapp.authentication.login.data.api.UserAPI
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
    fun provideUserApi(): UserAPI = ApiClient.create()

}
