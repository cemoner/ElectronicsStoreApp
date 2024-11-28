package com.example.fooddeliveryapp.profile.authentication.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.profile.authentication.login.data.model.request.SignUpRequest
import com.example.fooddeliveryapp.profile.authentication.login.domain.usecase.AuthUseCase
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.RegisterContract.SideEffect
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.RegisterContract.UiAction
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.RegisterContract.UiState
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,private val navigator: AppNavigator
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
            is UiAction.OnEmailChange -> onUserNameChange(action.email)
            is UiAction.OnPasswordChange -> onPasswordChange(action.password)
            is UiAction.OnPhoneChange -> onPhoneChange(action.phone)
            is UiAction.OnAddressChange -> onAddressChange(action.address)
            is UiAction.OnLoginButtonClick -> noArgNavigation(Destination.Login(),null)
        }
    }



    private fun noArgNavigation(destination: String,popUpTo:String?){
        navigator.tryNavigateTo(
            destination,
            popUpToRoute = popUpTo,
            inclusive = true,
            isSingleTop = true,
            restoreState = false,
            saveState = false
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

        if(inputValidation(uiState.value.email)){
            val response = authUseCase.signUp(
                SignUpRequest(
                    uiState.value.email,
                    uiState.value.password,
                    "${uiState.value.name} + ${uiState.value.surName}",
                    uiState.value.phone,uiState.value.address))


            when(response.status){
                200 -> {
                    onCreateToast(response.message)
                    delay(1000)
                    updateUiState(newUiState = uiState.value.copy(
                        showProgress = false,
                        email = "",
                        password = "",
                        name = "",
                        address = "",
                        surName = "",
                        phone = "",
                        ))
                    noArgNavigation(Destination.Login(), Destination.Login())
                }

                400 -> {
                    onCreateToast(response.message)
                    updateUiState(newUiState = uiState.value.copy(
                        showProgress = false,
                        email = "",
                        password = "",
                    ))
                }
            }
        }
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


private fun initialUiState(): UiState = UiState("","","","","",false, "","")
