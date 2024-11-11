package com.example.fooddeliveryapp.shared.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.login.presentation.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.UiState
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.UiAction
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.SideEffect
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor():ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()
) {

    override fun onAction(action:UiAction) {
        when (action) {
            is UiAction.OnLogout -> {
                IsLoggedInSingleton.setIsLoggedIn(false)
               onNavigateTo("Profile")
            }
            is UiAction.OnChangePassword -> onNavigateTo("PasswordChange")
            is UiAction.OnOrderHistory -> onNavigateTo("OrderHistory")
            is UiAction.OnAddressManagement -> onNavigateTo("AddressManagement")
            is UiAction.OnFavorites -> onNavigateTo("Favorites")
        }
    }


    private fun onNavigateTo(destination:String) {
        viewModelScope.launch {
            emitSideEffect(SideEffect.Navigate(destination))
        }
    }
}

private fun initialUiState(): UiState = UiState("")