package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.profile.authentication.login.data.api.AuthApi
import com.example.fooddeliveryapp.home.data.api.HomeApi
import com.example.fooddeliveryapp.home.data.api.ProductDetailApi
import com.example.fooddeliveryapp.retrofit.ApiClient
import com.example.fooddeliveryapp.profile.profile.data.api.ProfileApi
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
    fun providesAuthApi(): AuthApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesProfileApi(): ProfileApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesHomeApi(): HomeApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesProductDetailApi(): ProductDetailApi = ApiClient.create()

}
