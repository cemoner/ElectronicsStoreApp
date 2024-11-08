package com.example.fooddeliveryapp.authentication.login.presentation.contracts

interface ProfileContract {

    data class UiState(val name:String)

    sealed interface UiAction {
        object OnLogoutButton : UiAction
    }

    sealed interface SideEffect {
        data class Navigate(val route:String) : SideEffect
    }
}