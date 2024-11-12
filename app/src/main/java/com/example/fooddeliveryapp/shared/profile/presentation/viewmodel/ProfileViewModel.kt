package com.example.fooddeliveryapp.shared.profile.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import com.example.fooddeliveryapp.authentication.login.presentation.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.UiState
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.UiAction
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.SideEffect
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.shared.navigation.model.NavItem
import com.example.fooddeliveryapp.shared.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.shared.profile.data.model.response.UserResponse
import com.example.fooddeliveryapp.shared.profile.domain.usecase.ProfileUseCase
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase):ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()
) {

    override fun onAction(action:UiAction) {
        when (action) {
            is UiAction.OnLogout -> {
                IsLoggedInSingleton.setIsLoggedIn(false)
               onNavigateTo("Profile/{-1}")
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

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }

     fun checkIsLoggedIn() {
        if (!IsLoggedInSingleton.getIsLoggedIn()) {
            onNavigateTo(NavItem.Login.route)
        }
    }
    fun getUserData(userId:String) = viewModelScope.launch {
        val userResponse = profileUseCase.getUser(UserRequest("canerture", userId))
        if(userResponse.userId != null && userResponse.email != null && userResponse.name != null && userResponse.phone != null) {
            updateUiState(newUiState = uiState.value.copy(
                userId = userResponse.userId,
                email = userResponse.email,
                name = userResponse.name,
                phoneNumber = userResponse.phone

            ))
        }
    }
}

private fun initialUiState(): UiState = UiState("","","","")