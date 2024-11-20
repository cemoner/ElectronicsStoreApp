package com.example.fooddeliveryapp.features.profile.authentication.login.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.mvi.MVI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.contract.LoginContract.UiAction
import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.contract.LoginContract.UiState
import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.contract.LoginContract.SideEffect
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import com.example.fooddeliveryapp.features.profile.authentication.login.domain.usecase.AuthUseCase


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val navigator: AppNavigator
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

        val result = authUseCase.signIn(
            uiState.value.email,
            uiState.value.password
        )

        result.onSuccess {
            IsLoggedInSingleton.setIsLoggedIn(true)
            onCreateToast(it.message)
            UserIdSingleton.setUserId(it.userId)
            navigationToProfileWithArg(it.userId, Destination.Login())
            updateUiState(initialUiState())
        }
        result.onFailure {
            val errorMessage = it.message ?: "An unexpected error occurred"
            onCreateToast(errorMessage)
            updateUiState(initialUiState())
        }


    }
}

private fun initialUiState(): UiState = UiState("","","",false)
