package com.example.electronicsstoreapp.di.remote

import com.example.electronicsstoreapp.features.cart.data.api.CartActionApi
import com.example.electronicsstoreapp.features.cart.data.api.CartDataApi
import com.example.electronicsstoreapp.features.home.data.api.ProductActionApi
import com.example.electronicsstoreapp.features.home.data.api.ProductDataApi
import com.example.electronicsstoreapp.features.profile.authentication.login.data.api.AuthApi
import com.example.electronicsstoreapp.features.profile.profile.data.api.UserApi
import com.example.electronicsstoreapp.retrofit.ApiClient
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
    fun providesProfileApi(): UserApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesHomeApi(): ProductDataApi = ApiClient.create()


    @Provides
    @Singleton
    fun providesProductActionApi(): ProductActionApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesCartActionApi(): CartActionApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesCartDataApi(): CartDataApi = ApiClient.create()

}
