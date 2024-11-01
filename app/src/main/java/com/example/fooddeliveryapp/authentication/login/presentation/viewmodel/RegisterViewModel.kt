package com.example.fooddeliveryapp.authentication.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
):ViewModel() {

    private val _userNameText = MutableStateFlow("")
    val userNameText = _userNameText.asStateFlow()
    private val _passwordText = MutableStateFlow("")
    val passwordText = _passwordText.asStateFlow()
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
    private val _surName = MutableStateFlow("")
    val surName = _surName.asStateFlow()


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

    suspend fun onRegisterClick(email:String,password:String) {
        authRepository.signUp(email,password)
    }

}
