package com.example.fooddeliveryapp.authentication.login.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.components.EmailTextField
import com.example.fooddeliveryapp.authentication.components.FormTextField
import com.example.fooddeliveryapp.authentication.components.Password
import com.example.fooddeliveryapp.authentication.components.RegOrLoginDuo
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.UiState
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.SideEffect
import com.example.fooddeliveryapp.authentication.login.presentation.viewmodel.RegisterViewModel
import com.example.fooddeliveryapp.mvi.CollectSideEffect
import com.example.fooddeliveryapp.mvi.unpack
import kotlinx.coroutines.flow.Flow


@Composable
fun Register(navController: NavController){
    val viewModel: RegisterViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    Register(uiState,onAction,sideEffect,navController)
}


@Composable
fun Register(uiState: UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>, navController: NavController){
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.Navigate -> {
                navController.navigate(it.route)
            }
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    val topBias = 1f / 3.5f
    Column(modifier = Modifier.fillMaxSize().padding(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        FormTextField(uiState.name,{onAction(UiAction.OnNameChange(it))},"Name")
        FormTextField(uiState.surName,{onAction(UiAction.OnSurNameChange(it))},"Surname")
        FormTextField(uiState.phone,{onAction(UiAction.OnPhoneChange(it))},"Phone")
        FormTextField(uiState.address,{onAction(UiAction.OnAddressChange(it))},"Address")
        EmailTextField(emailText = uiState.email,{onAction(UiAction.OnEmailChange(it))})
        Password(uiState.password, {onAction(UiAction.OnPasswordChange(it))})
        if(uiState.showProgress){ CircularProgressIndicator()}
        Button(modifier = Modifier.padding(top = 6.dp),onClick = {onAction(UiAction.OnRegisterClick)}, shape = RoundedCornerShape(32.dp)) { Text("Register",modifier= Modifier.padding(7.5.dp), fontSize = 14.sp)}
        RegOrLoginDuo(navController = navController, route = "Profile", textString = "Already have an Account?", buttonString = "Log In")
    }

}