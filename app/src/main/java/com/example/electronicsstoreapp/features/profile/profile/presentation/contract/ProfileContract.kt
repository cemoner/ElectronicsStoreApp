package com.example.electronicsstoreapp.features.profile.profile.presentation.contract

sealed interface ProfileContract {
    data class UiState(
        val userId: String,
        val email: String,
        val name: String,
        val phoneNumber: String,
    )

    sealed interface UiAction {
        object OnLogoutButtonClicked : UiAction

        object OnChangePassword : UiAction

        object OnAddressManagement : UiAction

        object OnFavorites : UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(
            val message: String,
        ) : SideEffect
    }
}
