package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract

interface RegisterContract {

    data class UiState(val name:String,
                       val surName:String,
                       val email: String,
                       val password: String,
                       val phone:String,
                       val address:String,
                       val showProgress:Boolean
    )

    sealed interface UiAction {
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data class OnNameChange(val name: String) : UiAction
        data class OnSurNameChange(val surName: String) : UiAction
        data class OnPhoneChange(val phone: String) : UiAction
        data class OnAddressChange(val address: String) : UiAction
        object OnRegisterButtonClicked : UiAction
        object OnLoginButtonClicked : UiAction
        object OnBackButtonClicked:UiAction


    }

    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
    }
}