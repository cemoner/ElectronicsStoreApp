package com.example.fooddeliveryapp.authentication.login.presentation.contracts

interface RegisterContract {
    sealed interface UiState {

    }
    sealed interface UiAction {
        data class OnUserNameChange(val userName: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data class OnNameChange(val name: String) : UiAction
        data class OnSurNameChange(val surName: String) : UiAction
        object OnRegisterClick : UiAction

    }

    sealed interface SideEffect {
        object RegisterSuccess : SideEffect
        object LoginFail : SideEffect
    }
}