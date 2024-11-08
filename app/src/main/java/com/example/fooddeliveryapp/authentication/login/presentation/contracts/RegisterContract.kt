package com.example.fooddeliveryapp.authentication.login.presentation.contracts

interface RegisterContract {

    data class UiState(val name:String,val surName:String,val userName: String,val password: String,val errorText:String,val showProgress:Boolean)

    sealed interface UiAction {
        data class OnUserNameChange(val userName: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data class OnNameChange(val name: String) : UiAction
        data class OnSurNameChange(val surName: String) : UiAction
        object OnRegisterClick : UiAction

    }

    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
        data class Navigate(val route:String) : SideEffect
    }
}