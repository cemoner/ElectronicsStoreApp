package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.authentication.common.Resource
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
):ViewModel() {
    private val _passwordText = MutableStateFlow("")
    private val _userNameText = MutableStateFlow("")
    private val _navigationEvent = MutableStateFlow<String?>(null)
    private val _toastMessage = MutableStateFlow<String?>(null)
    val userNameText = _userNameText.asStateFlow()
    val passwordText = _passwordText.asStateFlow()
    val navigationEvent: StateFlow<String?> = _navigationEvent
    val toastMessage: StateFlow<String?> = _toastMessage  // Expose the toast message




    fun onAction(action: LoginContract.UiAction) {
        when (action) {
            is LoginContract.UiAction.OnLoginClick -> {
               viewModelScope.launch {
                   onLoginClick()
               }
            }
            is LoginContract.UiAction.OnUserNameChange -> onUserNameChange(action.userName)
            is LoginContract.UiAction.OnPasswordChange -> onPasswordChange(
                action.password
            )
        }
    }



    fun onNavigateTo(destination:String) {
        _navigationEvent.value = destination
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }

    fun onUserNameChange(text: String) {
        _userNameText.value = text
    }
    fun onPasswordChange(text: String) {
        _passwordText.value = text
    }

    suspend fun onLoginClick(){
        val result = authRepository.signIn(userNameText.value,passwordText.value)
        if(result is Resource.Success){
            IsLoggedInSingleton.setIsLoggedIn(true)
            onNavigateTo("Profile")
            _toastMessage.value = "Login Successful. You are now being redirected to the profile page"
        }
        if(result is Resource.Error){
            // onNavigateTo silinince toast çıkmıyor ?????????????????????????????
            _toastMessage.value = "Login Error. E-mail or password is incorrect. Try Again."
            onNavigateTo("Profile")
        }
    }
}
