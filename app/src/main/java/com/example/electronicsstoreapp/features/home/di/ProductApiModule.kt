package com.example.electronicsstoreapp.features.home.di

import com.example.electronicsstoreapp.features.cart.data.api.CartActionApi
import com.example.electronicsstoreapp.features.home.data.api.ProductActionApi
import com.example.electronicsstoreapp.features.home.data.api.ProductDataApi
import com.example.electronicsstoreapp.retrofit.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProductApiModule {
    @Provides
    @Singleton
    fun providesHomeApi(): ProductDataApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesProductActionApi(): ProductActionApi = ApiClient.create()

    @Provides
    @Singleton
    fun providesCartActionApi(): CartActionApi = ApiClient.create()

}
