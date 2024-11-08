package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.common.Resource
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.SideEffect
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.UiState
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
):ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.OnRegisterClick ->
            {
                viewModelScope.launch {
                    onRegisterClick()
                }
            }
            is UiAction.OnNameChange -> onNameChange(action.name)
            is UiAction.OnSurNameChange -> onSurNameChange(action.surName)
            is UiAction.OnUserNameChange -> onUserNameChange(action.userName)
            is UiAction.OnPasswordChange -> onPasswordChange(action.password)
        }
    }



    fun onNavigateTo(destination:String) {
        viewModelScope.launch {
            emitSideEffect(SideEffect.Navigate(destination))
        }
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }

    private fun onUserNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(userName = text))
    }
    private fun onPasswordChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(password = text))
    }

    private fun onNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(name = text))
    }

    private fun onSurNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(surName = text))
    }

    private fun onRegisterClick() = viewModelScope.launch {
        updateUiState(newUiState = uiState.value.copy(showProgress = true))
        delay(2500)
        val result = authRepository.signUp(uiState.value.userName,uiState.value.password)
        updateUiState(newUiState = uiState.value.copy(showProgress = false))
        if(result is Resource.Success){
            onNavigateTo("Profile")
            onCreateToast("Register Successful. You are now being redirected to the Login page")
        }
        if(result is Resource.Error){
            onCreateToast("Register Failed.Try Again.")
        }
    }

}

private fun initialUiState(): UiState = UiState("","","","","",false)
