package com.example.fooddeliveryapp.features.profile.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.features.profile.profile.presentation.contract.ProfileContract.UiState
import com.example.fooddeliveryapp.features.profile.profile.presentation.contract.ProfileContract.UiAction
import com.example.fooddeliveryapp.features.profile.profile.presentation.contract.ProfileContract.SideEffect
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import com.example.fooddeliveryapp.features.profile.profile.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val navigator: AppNavigator
    ):ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(
    initialUiState()
) {
    init{
        viewModelScope.launch {
            getUserData(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId())
        }
    }

    override fun onAction(action:UiAction) {
        when (action) {
            is UiAction.OnLogout -> {
                onLogout()
            }
            is UiAction.OnChangePassword -> {}
            is UiAction.OnOrderHistory -> {}
            is UiAction.OnAddressManagement -> {}
            is UiAction.OnFavorites -> {}

        }
    }

    private fun onLogout() {
        IsLoggedInSingleton.setIsLoggedIn(false)
        UserIdSingleton.setUserId("-1")
        onCreateToast("Logged out successfully")
        navigator.tryNavigateBack()
        navigator.tryNavigateTo(Destination.Login())
    }



    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }

    private fun getUserData(storeName:String, userId:String) = viewModelScope.launch {
        val userResponse = getUserUseCase(storeName,userId)
        userResponse.onSuccess {
            updateUiState(
                uiState.value.copy(
                    userId = it.userId,
                    email = it.email,
                    name = it.name,
                    phoneNumber = it.phone
                )
            )
        }
        userResponse.onFailure {
            onCreateToast(it.message.toString())
        }
    }
}

private fun initialUiState(): UiState = UiState("","","","")