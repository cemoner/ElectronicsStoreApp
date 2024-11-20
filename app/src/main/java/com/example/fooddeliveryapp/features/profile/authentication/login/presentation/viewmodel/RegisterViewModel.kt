package com.example.fooddeliveryapp.features.profile.authentication.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.features.profile.authentication.login.domain.usecase.SignUpUseCase
import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.contract.RegisterContract.SideEffect
import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.contract.RegisterContract.UiAction
import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.contract.RegisterContract.UiState
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase, private val navigator: AppNavigator
):ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.OnRegisterButtonClicked ->
            {
                viewModelScope.launch {
                    onRegisterClick()
                }
            }
            is UiAction.OnNameChange -> onNameChange(action.name)
            is UiAction.OnSurNameChange -> onSurNameChange(action.surName)
            is UiAction.OnEmailChange -> onUserNameChange(action.email)
            is UiAction.OnPasswordChange -> onPasswordChange(action.password)
            is UiAction.OnPhoneChange -> onPhoneChange(action.phone)
            is UiAction.OnAddressChange -> onAddressChange(action.address)
            is UiAction.OnLoginButtonClicked -> noArgNavigation(Destination.Login(),null)
            is UiAction.OnBackButtonClicked -> tryNavigateBack()
        }
    }

    fun tryNavigateBack(){
        viewModelScope.launch {
            navigator.tryNavigateBack()
        }
    }


    private fun noArgNavigation(destination: String,popUpTo:String?){
        navigator.tryNavigateTo(
            destination,
            popUpToRoute = popUpTo,
            inclusive = popUpTo != null,
            isSingleTop = true,
        )
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

    private fun onNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(name = text))
    }

    private fun onSurNameChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(surName = text))
    }
    private fun onPhoneChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(phone = text))
    }
    private fun onAddressChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(address = text))
    }

    private fun onRegisterClick() = viewModelScope.launch {

        updateUiState(newUiState = uiState.value.copy(showProgress = true))

        if (inputValidation(uiState.value.email)) {

            val result = signUpUseCase(
                uiState.value.email,
                uiState.value.password,
                "${uiState.value.name} + ${uiState.value.surName}",
                uiState.value.phone,
                uiState.value.address
            )

            result.onSuccess {
                noArgNavigation(Destination.Login(), Destination.Login())
                onCreateToast(it.message)
                updateUiState(initialUiState())
            }
            result.onFailure {
                val errorMessage = it.message ?: "An unexpected error occurred"
                onCreateToast(errorMessage)
                updateUiState(initialUiState())
            }
        }
        updateUiState(initialUiState())
    }

    private fun inputValidation(email:String):Boolean{
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        if (!emailRegex.matches(email)) {
            onCreateToast("Invalid email address")
            return false
        }
        return true
    }
}


private fun initialUiState(): UiState = UiState("","","","","","",false)
