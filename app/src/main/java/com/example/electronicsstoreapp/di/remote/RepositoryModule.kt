package com.example.electronicsstoreapp.di.remote

import com.example.electronicsstoreapp.features.cart.data.datasource.remote.CartActionRemoteDataSource
import com.example.electronicsstoreapp.features.cart.data.datasource.remote.CartDataRemoteDataSource
import com.example.electronicsstoreapp.features.cart.data.repository.CartActionActionRepositoryImpl
import com.example.electronicsstoreapp.features.cart.data.repository.CartDataRepositoryImpl
import com.example.electronicsstoreapp.features.cart.domain.repository.CartActionRepository
import com.example.electronicsstoreapp.features.cart.domain.repository.CartDataRepository
import com.example.electronicsstoreapp.features.home.data.datasource.remote.ProductActionRemoteDataSource
import com.example.electronicsstoreapp.features.home.data.datasource.remote.ProductDataRemoteDataSource
import com.example.electronicsstoreapp.features.home.data.repository.ProductActionRepositoryImpl
import com.example.electronicsstoreapp.features.home.data.repository.ProductDataRepositoryImpl
import com.example.electronicsstoreapp.features.home.domain.repository.ProductActionRepository
import com.example.electronicsstoreapp.features.home.domain.repository.ProductDataRepository
import com.example.electronicsstoreapp.features.profile.authentication.login.data.datasource.remote.AuthRemoteDataSource
import com.example.electronicsstoreapp.features.profile.authentication.login.data.repository.AuthenticationRepositoryImpl
import com.example.electronicsstoreapp.features.profile.authentication.login.domain.repository.AuthenticationRepository
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
    fun providesAuthenticationRepository(authRemoteDataSource: AuthRemoteDataSource): AuthenticationRepository =
        AuthenticationRepositoryImpl(authRemoteDataSource)

    @Provides
    fun providesUserRepository(userDataSource: UserDataSource): UserDataRepository =
        UserDataRepositoryImpl(userDataSource)

    @Provides
    fun providesProductDataRepository(productDataRemoteDataSource: ProductDataRemoteDataSource): ProductDataRepository =
        ProductDataRepositoryImpl(productDataRemoteDataSource)

    @Provides
    fun providesProductActionRepository(productActionRemoteDataSource: ProductActionRemoteDataSource): ProductActionRepository =
        ProductActionRepositoryImpl(productActionRemoteDataSource)

    @Provides
    fun providesCartActionRepository(cartActionRemoteDataSource: CartActionRemoteDataSource): CartActionRepository =
        CartActionActionRepositoryImpl(cartActionRemoteDataSource)

    @Provides
    fun providesCartDataRepository(cartDataRemoteDataSource: CartDataRemoteDataSource): CartDataRepository =
        CartDataRepositoryImpl(cartDataRemoteDataSource)
}