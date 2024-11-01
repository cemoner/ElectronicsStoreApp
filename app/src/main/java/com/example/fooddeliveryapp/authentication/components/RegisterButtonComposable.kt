package com.example.fooddeliveryapp.authentication.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.fooddeliveryapp.authentication.login.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch


@Composable
fun RegisterButton(navController: NavController,modifier: Modifier,route:String,viewModel: RegisterViewModel){
    val coroutineScope = rememberCoroutineScope()
    Button(onClick = {
        coroutineScope.launch {
            viewModel.onRegisterClick(
                viewModel.userNameText.value,
                viewModel.passwordText.value
            )
            // Navigate to the next screen after registration completes
            navController.navigate(route)
        }
                     },
        modifier = modifier,
        shape = RoundedCornerShape(32.dp)

    ) {
        Text("Register",modifier= Modifier.padding(7.5.dp), fontSize = 14.sp)
    }
}