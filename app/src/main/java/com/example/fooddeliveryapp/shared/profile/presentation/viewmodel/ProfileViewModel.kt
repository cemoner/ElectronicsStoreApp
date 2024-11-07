package com.example.fooddeliveryapp.shared.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.ProfileContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor():ViewModel() {
    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> = _navigationEvent

    fun onAction(action: ProfileContract.UiAction) {
        when (action) {
            is ProfileContract.UiAction.OnLogoutButton -> onLogoutClick()
            else -> {}
        }
    }


    private fun onNavigateTo(destination:String) {
        _navigationEvent.value = destination
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }

    private fun onLogoutClick(){
        IsLoggedInSingleton.setIsLoggedIn(false)
        onNavigateTo("Profile")
    }
}