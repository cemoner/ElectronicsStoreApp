package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel

import android.view.WindowInsets.Side
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.common.Resource
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.mvi.MVI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.UiState
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.SideEffect
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract
import com.example.fooddeliveryapp.mvi.mvi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toSet


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
):ViewModel(),MVI<UiState,UiAction,SideEffect> by mvi(initialUiState())  {


    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.OnLoginClick -> {
               viewModelScope.launch {
                   onLoginClick()
               }
            }
            is UiAction.OnUserNameChange -> {
                onUserNameChange(action.userName)
            }
            is UiAction.OnPasswordChange -> {
                onPasswordChange(action.password)
            }
        }
    }


    fun onNavigateTo(destination:String) {
        viewModelScope.launch {
            emitSideEffect(SideEffect.Navigate(destination))
        }
    }

    fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }

    fun onUserNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(userName = text))
    }
    fun onPasswordChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(password = text))
    }

    fun onLoginClick() = viewModelScope.launch {
        updateUiState(newUiState = uiState.value.copy(showProgress = true))
        delay(2500)
        val result = authRepository.signIn(uiState.value.userName,uiState.value.password)
        updateUiState(newUiState = uiState.value.copy(showProgress = false))
        if(result is Resource.Success){
            IsLoggedInSingleton.setIsLoggedIn(true)
            onNavigateTo("Profile")
            onCreateToast("Login Successful. You are now being redirected to the profile page")
        }
        if(result is Resource.Error){
            onCreateToast("Login Error. E-mail or password is incorrect. Try Again.")
        }
    }
}

private fun initialUiState(): UiState = UiState("","","",false)
