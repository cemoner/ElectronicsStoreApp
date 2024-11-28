package com.example.fooddeliveryapp.profile.profile.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.profile.profile.presentation.contracts.ProfileContract.UiState
import com.example.fooddeliveryapp.profile.profile.presentation.contracts.ProfileContract.UiAction
import com.example.fooddeliveryapp.profile.profile.presentation.contracts.ProfileContract.SideEffect
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import com.example.fooddeliveryapp.profile.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.profile.profile.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val navigator: AppNavigator
    ):ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()
) {
    val userId = savedStateHandle.get<String>("userId")
    init {

        if (userId != null && userId != "-1") {
            if (!isUserDataSaved()) {
                getUserData(userId.replace("{", "").replace("}", ""))
            } else {
                restoreSavedUserData()
            }
        }
    }

    override fun onAction(action:UiAction) {
        when (action) {
            is UiAction.OnLogout -> {
                IsLoggedInSingleton.setIsLoggedIn(false)
               onNavigateTo(Destination.Login(), Destination.Login())
            }
            is UiAction.OnChangePassword -> {}
            is UiAction.OnOrderHistory -> {}
            is UiAction.OnAddressManagement -> {}
            is UiAction.OnFavorites -> {}

        }
    }



    private fun onNavigateTo(destination: String,popUpTo:String) {
        navigator.tryNavigateTo(destination,popUpTo)
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }


    fun getUserData(userId:String) = viewModelScope.launch {
        val userResponse = profileUseCase.getUser(UserRequest("canerture", userId))
        if(userResponse.userId != null && userResponse.email != null && userResponse.name != null && userResponse.phone != null) {
            val newState = uiState.value.copy(
                userId = userResponse.userId,
                email = userResponse.email,
                name = userResponse.name,
                phoneNumber = userResponse.phone
            )
            updateUiState(newState)
            saveUserDataToStateHandle(newState)
        }
    }

    private fun isUserDataSaved(): Boolean {
        return savedStateHandle.contains("userId") &&
                savedStateHandle.contains("email") &&
                savedStateHandle.contains("name") &&
                savedStateHandle.contains("phoneNumber")
    }

    private fun restoreSavedUserData() {
        val restoredState = uiState.value.copy(
            userId = savedStateHandle["userId"] ?: "yo",
            email = savedStateHandle["email"] ?: "yo",
            name = savedStateHandle["name"] ?: "yo",
            phoneNumber = savedStateHandle["phoneNumber"] ?: "yoo"
        )
        updateUiState(restoredState)
    }

    private fun saveUserDataToStateHandle(state: UiState) {
        savedStateHandle["userId"] = state.userId
        savedStateHandle["email"] = state.email
        savedStateHandle["name"] = state.name
        savedStateHandle["phoneNumber"] = state.phoneNumber
    }
}

private fun initialUiState(): UiState = UiState("","","","")