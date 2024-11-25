package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.features.home.domain.usecase.GetFavoritesUseCase
import com.example.electronicsstoreapp.features.profile.authentication.login.domain.usecase.SignInUseCase
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract.LoginContract.SideEffect
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract.LoginContract.UiAction
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract.LoginContract.UiState
import com.example.electronicsstoreapp.main.util.FavoritesSingleton
import com.example.electronicsstoreapp.main.util.IsLoggedInSingleton
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.main.util.UserIdSingleton
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.mvi.mvi
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val signInUseCase: SignInUseCase,
        private val navigator: AppNavigator,
        private val getFavoritesUseCase: GetFavoritesUseCase,
    ) : ViewModel(),
        MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {
        override fun onAction(action: UiAction) {
            when (action) {
                is UiAction.OnLoginButtonClicked -> {
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
                is UiAction.OnRegisterButtonClicked -> {
                    noArgNavigation(Destination.Register(), Destination.Login())
                }

                is UiAction.OnBackButtonClicked -> tryNavigateBack()
            }
        }

        private fun noArgNavigation(
            destination: String,
            popUpTo: String?,
        ) {
            navigator.tryNavigateTo(
                destination,
                popUpToRoute = popUpTo,
            )
        }

        fun tryNavigateBack() {
            viewModelScope.launch {
                navigator.tryNavigateBack()
            }
        }

        private fun navigationToProfileWithArg(
            id: String,
            popUpTo: String?,
        ) {
            navigator.tryNavigateTo(
                Destination.Profile(userId = id),
                popUpToRoute = popUpTo,
                inclusive = true,
            )
        }

        private fun onCreateToast(message: String) {
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

        private fun onLoginClick() =
            viewModelScope.launch {
                updateUiState(newUiState = uiState.value.copy(showProgress = true))

                val result =
                    signInUseCase(
                        uiState.value.email,
                        uiState.value.password,
                    )

                result.onSuccess {
                    IsLoggedInSingleton.setIsLoggedIn(true)
                    onCreateToast(it.message)
                    UserIdSingleton.setUserId(it.userId)
                    val favoritesResult = getFavoritesUseCase(StoreNameSingleton.getStoreName(), it.userId)
                    favoritesResult.onSuccess {
                        it.forEach {
                            FavoritesSingleton.addToFavorites(it.id)
                        }
                    }
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

private fun initialUiState(): UiState = UiState("", "", "", false)
