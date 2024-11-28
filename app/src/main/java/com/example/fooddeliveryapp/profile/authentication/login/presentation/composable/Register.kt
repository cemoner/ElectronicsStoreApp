package com.example.fooddeliveryapp.profile.authentication.login.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fooddeliveryapp.mvi.CollectSideEffect
import com.example.fooddeliveryapp.mvi.unpack
import com.example.fooddeliveryapp.profile.authentication.login.presentation.components.FormTextFieldContent
import com.example.fooddeliveryapp.profile.authentication.login.presentation.viewmodel.RegisterViewModel
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.RegisterContract.UiState
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.RegisterContract.SideEffect
import com.example.fooddeliveryapp.profile.authentication.login.presentation.contracts.RegisterContract.UiAction
import com.example.fooddeliveryapp.profile.authentication.login.presentation.components.EmailTextField
import com.example.fooddeliveryapp.profile.authentication.login.presentation.components.RegOrLoginDuo
import com.example.fooddeliveryapp.profile.authentication.login.presentation.components.PasswordTextField
import kotlinx.coroutines.flow.Flow


@Composable
fun RegisterScreen(){
    val viewModel: RegisterViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    RegisterContent(uiState,onAction,sideEffect)
}


@Composable
fun RegisterContent(uiState: UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>){
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        FormTextFieldContent(uiState.name,{onAction(UiAction.OnNameChange(it))},"Name")
        FormTextFieldContent(uiState.surName,{onAction(UiAction.OnSurNameChange(it))},"Surname")
        FormTextFieldContent(uiState.phone,{onAction(UiAction.OnPhoneChange(it))},"Phone")
        FormTextFieldContent(uiState.address,{onAction(UiAction.OnAddressChange(it))},"Address")
        EmailTextField(emailText = uiState.email,{onAction(UiAction.OnEmailChange(it))})
        PasswordTextField(uiState.password, {onAction(UiAction.OnPasswordChange(it))},{onAction(UiAction.OnRegisterClick)})
        if(uiState.showProgress){ CircularProgressIndicator()}
        Button(modifier = Modifier.padding(top = 6.dp),onClick = {onAction(UiAction.OnRegisterClick)}, shape = RoundedCornerShape(32.dp)) { Text("Register",modifier= Modifier.padding(7.5.dp), fontSize = 14.sp)}
        RegOrLoginDuo({onAction(UiAction.OnLoginButtonClick)},textString = "Already have an Account?", buttonString = "Log In")
    }

}