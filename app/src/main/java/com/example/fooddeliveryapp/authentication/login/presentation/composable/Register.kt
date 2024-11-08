package com.example.fooddeliveryapp.authentication.login.presentation.composable

import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.components.Password
import com.example.fooddeliveryapp.authentication.components.RegOrLoginDuo
import com.example.fooddeliveryapp.authentication.components.UserName
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.UiAction
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.UiState
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.RegisterContract.SideEffect
import com.example.fooddeliveryapp.authentication.login.presentation.viewmodel.LoginViewModel
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
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (username, password,name,surname,register,loginRow,progressBar) = createRefs()

        TextField(value = uiState.name,
            onValueChange = {onAction(UiAction.OnNameChange(it))},
            label = {Text("Name")},
            placeholder = {Text("Enter your name")},
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.DriveFileRenameOutline,
                    contentDescription = "",
                    tint = Color.Gray
                )}
            ,
            modifier = Modifier.constrainAs(name){
                linkTo(top = parent.top,bottom=parent.bottom,bias = topBias)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width= Dimension.fillToConstraints
            }
            )
        TextField(value = uiState.surName,
            onValueChange = {onAction(UiAction.OnSurNameChange(it))},
            label = {Text("Surname")},
            placeholder = {Text("Enter your surname")},
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, // Removes the underline when focused
                unfocusedIndicatorColor = Color.Transparent, // Removes the underline when not focused
                cursorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                Icons.Default.DriveFileRenameOutline,
                contentDescription = "",
                tint = Color.Gray
            )}
            ,
            modifier = Modifier.constrainAs(surname){
                top.linkTo(name.bottom, margin = 16.dp)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width= Dimension.fillToConstraints
            }
        )
        UserName(userNameText = uiState.userName,
            function = {onAction(UiAction.OnUserNameChange(it))},
            modifier = Modifier.constrainAs(username){
                top.linkTo(surname.bottom,margin=14.dp)
                start.linkTo(parent.start,margin=16.dp)
                end.linkTo(parent.end,margin=16.dp)
                width = Dimension.fillToConstraints })

        Password(uiState.password,
            {onAction(UiAction.OnPasswordChange(it))},
            Modifier.constrainAs(password){
                top.linkTo(username.bottom,margin=16.dp)
                start.linkTo(parent.start,margin=16.dp)
                end.linkTo(parent.end,margin=16.dp)
                width = Dimension.fillToConstraints
            })

        Button(onClick = {onAction(UiAction.OnRegisterClick)},
            modifier = Modifier.constrainAs(register) {
                top.linkTo(password.bottom, margin = 20.dp)
                end.linkTo(parent.end, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
            ,shape = RoundedCornerShape(32.dp)
        ) {
            Text("Register",modifier= Modifier.padding(7.5.dp), fontSize = 14.sp)
        }
        if(uiState.showProgress){
            CircularProgressIndicator(modifier = Modifier.constrainAs(progressBar){
                top.linkTo(register.bottom, margin = 16.dp)
                end.linkTo(parent.end,margin=16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width=Dimension.wrapContent
            })
        }

        RegOrLoginDuo(modifier = Modifier
            .constrainAs(loginRow) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                width = Dimension.fillToConstraints
            }, navController = navController, route = "Profile", textString = "Already have an Account?", buttonString = "Log In")
    }

}