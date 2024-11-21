package com.example.electronicsstoreapp.di.navigation

import com.example.electronicsstoreapp.navigation.navigator.AppNavigator
import com.example.electronicsstoreapp.navigation.navigator.AppNavigatorImpl
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