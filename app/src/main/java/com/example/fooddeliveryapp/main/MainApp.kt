package com.example.fooddeliveryapp.main

import android.app.Application
import com.example.fooddeliveryapp.authentication.login.presentation.util.IsLoggedInSingleton
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApp:Application() {
}