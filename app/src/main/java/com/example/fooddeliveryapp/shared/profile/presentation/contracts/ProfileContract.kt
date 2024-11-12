package com.example.fooddeliveryapp.shared.profile.presentation.contracts

interface ProfileContract {

    data class UiState(val userId:String,val email:String ,val name:String,val phoneNumber:String)

    sealed interface UiAction {
        object OnLogout : UiAction
        object OnChangePassword : UiAction
        object OnOrderHistory : UiAction
        object OnAddressManagement : UiAction
        object OnFavorites : UiAction
    }

    sealed interface SideEffect {
        data class Navigate(val route:String) : SideEffect
        data class ShowToast(val message:String) : SideEffect
    }
}