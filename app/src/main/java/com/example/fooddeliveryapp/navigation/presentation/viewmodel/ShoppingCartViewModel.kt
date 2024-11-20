package com.example.fooddeliveryapp.navigation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    val navigator: AppNavigator
) : ViewModel()
{
    fun tryNavigateTo(destination: String) {
        navigator.tryNavigateTo(destination)
    }
}
