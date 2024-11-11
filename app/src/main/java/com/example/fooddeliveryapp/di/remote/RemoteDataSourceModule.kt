package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.authentication.login.data.api.AuthAPI
import com.example.fooddeliveryapp.authentication.login.data.datasource.remote.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun providesDataSource(authApi: AuthAPI): RetrofitDataSource = RetrofitDataSource(authApi = authApi)

}
