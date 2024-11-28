package com.example.fooddeliveryapp.profile.authentication.login.presentation.viewmodel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.profile.authentication.login.data.model.request.SignInRequest
import com.example.fooddeliveryapp.profile.authentication.login.domain.model.AuthDetail
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.mvi.MVI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.LoginContract.UiAction
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.LoginContract.UiState
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.LoginContract.SideEffect
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import com.example.fooddeliveryapp.profile.authentication.login.domain.usecase.AuthUseCase


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val savedStateHandle: SavedStateHandle, private val navigator: AppNavigator
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
            is UiAction.OnRegisterButtonClick -> {
                noArgNavigation(Destination.Register(), Destination.Login())
            }
        }
    }

    private fun noArgNavigation(destination: String,popUpTo:String?){
        navigator.tryNavigateTo(
            destination,
            popUpToRoute = popUpTo,
        )
    }

    private fun navigationToProfileWithArg(id:String,popUpTo: String?){
        navigator.tryNavigateTo(
            Destination.Profile(userId = id),
            popUpToRoute = popUpTo,
            inclusive = true,
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

    private fun onLoginClick() = viewModelScope.launch {

        updateUiState(newUiState = uiState.value.copy(showProgress = true))

        val response:AuthDetail = authUseCase.signIn(
            SignInRequest(
                uiState.value.email.trim(),
                uiState.value.password))

        when(response.status){
            200 -> {
                IsLoggedInSingleton.setIsLoggedIn(true)
                onCreateToast(response.message)
                response.userId?.let { UserIdSingleton.setUserId(it) }
                response.userId?.let { navigationToProfileWithArg(it, Destination.Login()) }
                updateUiState(newUiState = uiState.value.copy(showProgress = false))

            }

            400 -> {
                onCreateToast(response.message)
                updateUiState(newUiState = uiState.value.copy(
                    showProgress = false, email = "", password = ""))
            }
        }
    }
}

private fun initialUiState(): UiState = UiState("","","",false)
