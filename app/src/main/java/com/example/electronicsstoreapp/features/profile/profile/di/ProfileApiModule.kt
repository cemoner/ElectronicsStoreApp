package com.example.electronicsstoreapp.features.profile.profile.di

import com.example.electronicsstoreapp.features.profile.profile.data.api.UserApi
import com.example.electronicsstoreapp.retrofit.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProfileApiModule {

    @Provides
    @Singleton
    fun providesProfileApi(): UserApi = ApiClient.create()
}