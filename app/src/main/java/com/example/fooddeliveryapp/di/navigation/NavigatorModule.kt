package com.example.fooddeliveryapp.di.navigation

import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import com.example.fooddeliveryapp.navigation.navigator.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NavigatorModule {

    @Provides
    @Singleton
    fun providesNavigator(): AppNavigator = AppNavigatorImpl()
}