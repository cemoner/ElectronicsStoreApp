package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract

interface LoginContract {
    data class UiState(
        val email: String,
        val password: String,
        val errorText: String,
        val showProgress: Boolean,
    )

    sealed interface UiAction {
        object OnLoginButtonClicked : UiAction

        data class OnEmailChange(
            val email: String,
        ) : UiAction

        data class OnPasswordChange(
            val password: String,
        ) : UiAction

        object OnRegisterButtonClicked : UiAction

        object OnBackButtonClicked : UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(
            val message: String,
        ) : SideEffect
    }
}
