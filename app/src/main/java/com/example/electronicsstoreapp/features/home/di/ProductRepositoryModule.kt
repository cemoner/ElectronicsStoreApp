package com.example.electronicsstoreapp.features.home.di

import com.example.electronicsstoreapp.features.home.data.datasource.remote.ProductActionRemoteDataSource
import com.example.electronicsstoreapp.features.home.data.datasource.remote.ProductDataRemoteDataSource
import com.example.electronicsstoreapp.features.home.data.repository.ProductActionRepositoryImpl
import com.example.electronicsstoreapp.features.home.data.repository.ProductDataRepositoryImpl
import com.example.electronicsstoreapp.features.home.domain.repository.ProductActionRepository
import com.example.electronicsstoreapp.features.home.domain.repository.ProductDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ProductRepositoryModule {
    @Provides
    fun providesProductDataRepository(productDataRemoteDataSource: ProductDataRemoteDataSource): ProductDataRepository = ProductDataRepositoryImpl(productDataRemoteDataSource)

    @Provides
    fun providesProductActionRepository(productActionRemoteDataSource: ProductActionRemoteDataSource): ProductActionRepository = ProductActionRepositoryImpl(productActionRemoteDataSource)
}