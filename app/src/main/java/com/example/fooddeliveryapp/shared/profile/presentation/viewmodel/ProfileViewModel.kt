package com.example.fooddeliveryapp.shared.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.ProfileContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.ProfileContract.SideEffect
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.ProfileContract.UiState
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
            is UiAction.OnLogoutButton -> onLogoutClick()
            else -> {}
        }
    }


    private fun onNavigateTo(destination:String) {
        viewModelScope.launch {
            emitSideEffect(SideEffect.Navigate(destination))
        }
    }

    private fun onLogoutClick(){
        IsLoggedInSingleton.setIsLoggedIn(false)
        onNavigateTo("Profile")
    }
}

private fun initialUiState(): UiState = UiState("")