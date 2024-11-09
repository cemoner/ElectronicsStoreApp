package com.example.fooddeliveryapp.authentication.login.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.authentication.components.Password
import com.example.fooddeliveryapp.authentication.components.RegOrLoginDuo
import com.example.fooddeliveryapp.authentication.components.UserName
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
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (username,password,normalLogin,registerRow,progressBar) = createRefs()

        UserName(userNameText = uiState.userName ,
            function = { onAction(UiAction.OnUserNameChange(it)) },
            modifier = Modifier.constrainAs(username){
                linkTo(top = parent.top, bottom = parent.bottom, bias = topBias)
                start.linkTo(parent.start,margin=16.dp)
                end.linkTo(parent.end,margin=16.dp)
                width = Dimension.fillToConstraints })

        Password(uiState.password,
            { onAction(UiAction.OnPasswordChange(it)) },
            Modifier.constrainAs(password){
                top.linkTo(username.bottom,margin=16.dp)
                start.linkTo(parent.start,margin=16.dp)
                end.linkTo(parent.end,margin=16.dp)
                width = Dimension.fillToConstraints
            })
        Button(onClick = {onAction(UiAction.OnLoginClick) },
            modifier = Modifier.constrainAs(normalLogin){
                top.linkTo(password.bottom, margin = 16.dp)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width=Dimension.fillToConstraints
            },
            shape = RoundedCornerShape(32.dp)

        ) {
            Text("Log In",modifier=Modifier.padding(7.5.dp), fontSize = 14.sp)
        }

        if(uiState.showProgress){
            CircularProgressIndicator(modifier = Modifier.constrainAs(progressBar){
                top.linkTo(normalLogin.bottom, margin = 16.dp)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width=Dimension.wrapContent
            })
        }
        RegOrLoginDuo(modifier = Modifier
            .constrainAs(registerRow) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width = Dimension.fillToConstraints
            }, navController = navController, route = "Register", textString = "Don't have an Account?", buttonString = "Register")
    }

}