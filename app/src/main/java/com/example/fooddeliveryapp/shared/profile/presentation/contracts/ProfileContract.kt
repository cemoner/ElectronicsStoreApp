package com.example.fooddeliveryapp.authentication.login.presentation.contracts

interface ProfileContract {
    sealed interface UiState {

    }
    sealed interface UiAction {
        object OnLogoutButton : UiAction

    }
}