package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.authentication.login.data.datasource.remote.RetrofitDataSource as AuthRetrofit
import com.example.fooddeliveryapp.shared.profile.data.datasource.remote.RetrofitDataSource as ProfileRetrofit
import com.example.fooddeliveryapp.authentication.login.data.repository.AuthenticationRepositoryImpl
import com.example.fooddeliveryapp.authentication.login.domain.repository.AuthenticationRepository
import com.example.fooddeliveryapp.shared.profile.data.repository.ProfileRepositoryImpl
import com.example.fooddeliveryapp.shared.profile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(authRetrofit: AuthRetrofit): AuthenticationRepository =  AuthenticationRepositoryImpl(authRetrofit)

    @Provides
    @Singleton
    fun provideProfileRepository(profileRetrofit: ProfileRetrofit): ProfileRepository =  ProfileRepositoryImpl(profileRetrofit)

}