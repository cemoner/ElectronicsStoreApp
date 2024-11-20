package com.example.fooddeliveryapp.di.remote

import com.example.fooddeliveryapp.features.cart.data.datasource.remote.CartActionRemoteDataSource
import com.example.fooddeliveryapp.features.cart.data.datasource.remote.CartDataRemoteDataSource
import com.example.fooddeliveryapp.features.cart.data.repository.CartActionActionRepositoryImpl
import com.example.fooddeliveryapp.features.cart.data.repository.CartDataRepositoryImpl
import com.example.fooddeliveryapp.features.cart.domain.repository.CartActionRepository
import com.example.fooddeliveryapp.features.cart.domain.repository.CartDataRepository
import com.example.fooddeliveryapp.features.favorites.data.datasource.remote.FavoritesActionRemoteDataSource
import com.example.fooddeliveryapp.features.favorites.data.datasource.remote.FavoritesDataRemoteDataSource
import com.example.fooddeliveryapp.features.favorites.data.repository.FavoritesActionRepositoryImpl
import com.example.fooddeliveryapp.features.favorites.data.repository.FavoritesDataRepositoryImpl
import com.example.fooddeliveryapp.features.favorites.domain.repository.FavoritesActionRepository
import com.example.fooddeliveryapp.features.favorites.domain.repository.FavoritesDataRepository
import com.example.fooddeliveryapp.features.home.data.datasource.remote.ProductActionRemoteDataSource
import com.example.fooddeliveryapp.features.home.data.datasource.remote.ProductDataRemoteDataSource
import com.example.fooddeliveryapp.features.home.data.repository.ProductActionRepositoryImpl
import com.example.fooddeliveryapp.features.home.data.repository.ProductDataRepositoryImpl
import com.example.fooddeliveryapp.features.home.domain.repository.ProductActionRepository
import com.example.fooddeliveryapp.features.home.domain.repository.ProductDataRepository
import com.example.fooddeliveryapp.features.profile.authentication.login.data.datasource.remote.AuthRemoteDataSource
import com.example.fooddeliveryapp.features.profile.authentication.login.data.repository.AuthenticationRepositoryImpl
import com.example.fooddeliveryapp.features.profile.authentication.login.domain.repository.AuthenticationRepository
import com.example.fooddeliveryapp.features.profile.profile.data.datasource.remote.UserDataSource
import com.example.fooddeliveryapp.features.profile.profile.data.repository.UserDataRepositoryImpl
import com.example.fooddeliveryapp.features.profile.profile.domain.repository.UserDataRepository
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

    @Provides
    fun providesFavoritesDataRepository(favoritesDataRemoteDataSource: FavoritesDataRemoteDataSource): FavoritesDataRepository =
        FavoritesDataRepositoryImpl(favoritesDataRemoteDataSource)

    @Provides
    fun providesFavoritesActionRepository(favoritesActionRemoteDataSource: FavoritesActionRemoteDataSource): FavoritesActionRepository =
        FavoritesActionRepositoryImpl(favoritesActionRemoteDataSource)

}