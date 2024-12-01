package com.example.electronicsstoreapp.features.cart.di

import com.example.electronicsstoreapp.features.cart.data.datasource.remote.CartActionRemoteDataSource
import com.example.electronicsstoreapp.features.cart.data.datasource.remote.CartDataRemoteDataSource
import com.example.electronicsstoreapp.features.cart.data.repository.CartActionActionRepositoryImpl
import com.example.electronicsstoreapp.features.cart.data.repository.CartDataRepositoryImpl
import com.example.electronicsstoreapp.features.cart.domain.repository.CartActionRepository
import com.example.electronicsstoreapp.features.cart.domain.repository.CartDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CartRepositoryModule {
    @Provides
    fun providesCartActionRepository(cartActionRemoteDataSource: CartActionRemoteDataSource): CartActionRepository =
        CartActionActionRepositoryImpl(cartActionRemoteDataSource)

    @Provides
    fun providesCartDataRepository(cartDataRemoteDataSource: CartDataRemoteDataSource): CartDataRepository =
        CartDataRepositoryImpl(cartDataRemoteDataSource)
}