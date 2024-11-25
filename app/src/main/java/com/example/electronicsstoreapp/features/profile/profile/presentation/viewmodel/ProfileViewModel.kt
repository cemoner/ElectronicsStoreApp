package com.example.electronicsstoreapp.features.profile.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.main.util.IsLoggedInSingleton
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.main.util.UserIdSingleton
import com.example.electronicsstoreapp.features.profile.profile.presentation.contract.ProfileContract.UiState
import com.example.electronicsstoreapp.features.profile.profile.presentation.contract.ProfileContract.UiAction
import com.example.electronicsstoreapp.features.profile.profile.presentation.contract.ProfileContract.SideEffect
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.mvi.mvi
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.navigator.AppNavigator
import com.example.electronicsstoreapp.features.profile.profile.domain.usecase.GetUserUseCase
import com.example.electronicsstoreapp.main.util.FavoritesSingleton
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
            is UiAction.OnLogoutButtonClicked -> onLogout(Destination.Login())
            is UiAction.OnChangePassword -> {}
            is UiAction.OnOrderHistory -> {}
            is UiAction.OnAddressManagement -> {}
            is UiAction.OnFavorites -> {tryNavigateTo(Destination.Favorites())}

        }
    }

    private fun onLogout(destination: String) {
        IsLoggedInSingleton.setIsLoggedIn(false)
        UserIdSingleton.setUserId("-1")
        if(FavoritesSingleton.favorites.isNotEmpty()){
            FavoritesSingleton.clearFavorites()
        }
        onCreateToast("Logged out successfully")
        navigator.clearBackStack()
        navigator.tryNavigateTo(Destination.Login())
    }

    private fun tryNavigateTo(destination:String){
        navigator.tryNavigateTo(destination)
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