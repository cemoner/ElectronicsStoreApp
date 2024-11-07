package com.example.fooddeliveryapp.authentication.login.presentation.contracts

interface LoginContract {
    sealed interface UiState {

    }
    sealed interface UiAction {
        data class OnUserNameChange(val userName: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        object OnLoginClick : UiAction

    }
}