package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.common.Resource
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
):ViewModel() {

    private val _userNameText = MutableStateFlow("")
    private val _passwordText = MutableStateFlow("")
    private val _surName = MutableStateFlow("")
    private val _navigationEvent = MutableStateFlow<String?>(null)
    private val _name = MutableStateFlow("")

    val userNameText = _userNameText.asStateFlow()
    val passwordText = _passwordText.asStateFlow()
    val name = _name.asStateFlow()
    val surName = _surName.asStateFlow()
    val navigationEvent: StateFlow<String?> = _navigationEvent

    fun onAction(action: RegisterContract.UiAction) {
        when (action) {
            is RegisterContract.UiAction.OnRegisterClick ->
            {
                viewModelScope.launch {
                    onRegisterClick()
                }
            }
            is RegisterContract.UiAction.OnNameChange -> onNameChange(action.name)
            is RegisterContract.UiAction.OnSurNameChange -> onSurNameChange(action.surName)
            is RegisterContract.UiAction.OnUserNameChange -> onUserNameChange(action.userName)
            is RegisterContract.UiAction.OnPasswordChange -> onPasswordChange(action.password)
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

    fun onNameChange(text: String) {
        _name.value = text
    }

    fun onSurNameChange(text: String) {
        _surName.value = text
    }

    suspend fun onRegisterClick() {
        var result = authRepository.signUp(userNameText.value,passwordText.value)
        if (result is Resource.Success) {
            onNavigateTo("Profile")
        }
    }

}
