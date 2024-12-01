package com.example.electronicsstoreapp.features.cart.di

import com.example.electronicsstoreapp.features.cart.data.api.CartDataApi
import com.example.electronicsstoreapp.retrofit.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CartApiModule {

    @Provides
    @Singleton
    fun providesCartDataApi(): CartDataApi = ApiClient.create()
}