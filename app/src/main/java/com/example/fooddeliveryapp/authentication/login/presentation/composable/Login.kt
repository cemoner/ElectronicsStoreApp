package com.example.fooddeliveryapp.authentication.login.presentation.composable

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
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.components.EmailTextField
import com.example.fooddeliveryapp.authentication.components.Password
import com.example.fooddeliveryapp.authentication.components.RegOrLoginDuo
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.UiState
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract.SideEffect
import com.example.fooddeliveryapp.authentication.login.presentation.viewmodel.LoginViewModel
import com.example.fooddeliveryapp.mvi.CollectSideEffect
import com.example.fooddeliveryapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun Login(navController: NavController){
    val viewModel:LoginViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    Login(uiState,onAction,sideEffect,navController)
}


@Composable
fun Login(uiState: UiState, onAction:(UiAction) -> Unit, sideEffect: Flow<SideEffect>, navController: NavController){
    val topBias = 1.15f / 3.5f
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
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        EmailTextField(emailText = uiState.email ,
            function = { onAction(UiAction.OnEmailChange(it)) })

        Password(uiState.password,
            { onAction(UiAction.OnPasswordChange(it)) })
        Button(modifier = Modifier.padding(top = 8.dp),onClick = {onAction(UiAction.OnLoginClick) },
            shape = RoundedCornerShape(32.dp)

        ) {
            Text("Log In",modifier=Modifier.padding(7.5.dp), fontSize = 14.sp)
        }

        if(uiState.showProgress){
            CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
        }
        RegOrLoginDuo(navController = navController, route = "Register", textString = "Don't have an Account?", buttonString = "Register")
    }

}