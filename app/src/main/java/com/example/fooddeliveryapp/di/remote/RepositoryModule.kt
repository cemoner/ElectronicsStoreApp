package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.profile.authentication.login.data.datasource.remote.retrofit.RetrofitDataSource as AuthRetrofit
import com.example.fooddeliveryapp.profile.profile.data.datasource.remote.retrofit.RetrofitDataSource as ProfileRetrofit
import com.example.fooddeliveryapp.home.data.datasource.remote.retrofit.RetrofitDataSource as HomeRetrofit
import com.example.fooddeliveryapp.home.data.repository.HomeRepositoryImpl
import com.example.fooddeliveryapp.home.data.repository.ProductDetailRepositoryImpl
import com.example.fooddeliveryapp.home.domain.repository.HomeRepository
import com.example.fooddeliveryapp.home.domain.repository.ProductDetailRepository
import com.example.fooddeliveryapp.profile.authentication.login.data.repository.AuthenticationRepositoryImpl
import com.example.fooddeliveryapp.profile.authentication.login.domain.repository.AuthenticationRepository
import com.example.fooddeliveryapp.profile.profile.data.repository.ProfileRepositoryImpl
import com.example.fooddeliveryapp.profile.profile.domain.repository.ProfileRepository
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
    fun providesAuthenticationRepository(authRetrofit: AuthRetrofit): AuthenticationRepository =  AuthenticationRepositoryImpl(authRetrofit)

    @Provides
    @Singleton
    fun providesProfileRepository(profileRetrofit: ProfileRetrofit): ProfileRepository =  ProfileRepositoryImpl(profileRetrofit)

    @Provides
    @Singleton
    fun providesHomeRepository(homeRetrofit:HomeRetrofit): HomeRepository = HomeRepositoryImpl(homeRetrofit)

    @Provides
    @Singleton
    fun providesProductDetailRepository(homeRetrofit:HomeRetrofit): ProductDetailRepository = ProductDetailRepositoryImpl(homeRetrofit)

}