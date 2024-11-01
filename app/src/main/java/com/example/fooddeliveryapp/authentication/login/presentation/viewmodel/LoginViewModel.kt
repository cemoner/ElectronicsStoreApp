package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.common.Resource
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
):ViewModel() {

    private val _userNameText = MutableStateFlow("")
    val userNameText = _userNameText.asStateFlow()

    private val _passwordText = MutableStateFlow("")
    val passwordText = _passwordText.asStateFlow()

    fun onUserNameChange(text: String) {
        _userNameText.value = text
    }
    fun onPasswordChange(text: String) {
        _passwordText.value = text
    }

    suspend fun onLoginClick(email:String,password:String): Resource<String> {
        return authRepository.signIn(email,password)
    }
}
