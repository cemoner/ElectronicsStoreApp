package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.authentication.login.data.model.response.AuthResponse
import com.example.fooddeliveryapp.authentication.login.domain.usecase.AuthUseCase
import com.example.fooddeliveryapp.authentication.login.presentation.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.mvi.MVI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.UiState
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.SideEffect
import com.example.fooddeliveryapp.mvi.mvi


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
):ViewModel(),MVI<UiState,UiAction,SideEffect> by mvi(initialUiState())  {


    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.OnLoginClick -> {
               viewModelScope.launch {
                   onLoginClick()
               }
            }
            is UiAction.OnEmailChange -> {
                onUserNameChange(action.email)
            }
            is UiAction.OnPasswordChange -> {
                onPasswordChange(action.password)
            }
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

    private fun onUserNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(email = text))
    }
    private fun onPasswordChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(password = text))
    }

    private fun onLoginClick() = viewModelScope.launch {

        updateUiState(newUiState = uiState.value.copy(showProgress = true))

        val response:AuthResponse = authUseCase.signIn(
            SignInRequest(
                uiState.value.email.trim(),
                uiState.value.password))

        when(response.status){
            200 -> {
                IsLoggedInSingleton.setIsLoggedIn(true)
                onNavigateTo("Profile")
                onCreateToast(response.message)
                updateUiState(newUiState = uiState.value.copy(showProgress = false))

            }

            400 -> {
                onCreateToast(response.message)
                updateUiState(newUiState = uiState.value.copy(showProgress = false))
            }
        }



    }
}

private fun initialUiState(): UiState = UiState("","","",false)
