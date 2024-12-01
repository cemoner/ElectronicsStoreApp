package com.example.electronicsstoreapp.features.profile.profile.di

import com.example.electronicsstoreapp.features.profile.profile.data.datasource.remote.UserDataSource
import com.example.electronicsstoreapp.features.profile.profile.data.repository.UserDataRepositoryImpl
import com.example.electronicsstoreapp.features.profile.profile.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun providesUserRepository(userDataSource: UserDataSource): UserDataRepository =  UserDataRepositoryImpl(userDataSource)

}