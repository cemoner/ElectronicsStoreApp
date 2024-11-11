package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.authentication.login.data.api.AuthAPI
import com.example.fooddeliveryapp.retrofit.ApiClient
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
    fun provideAuthAPI(): AuthAPI = ApiClient.create()

}
