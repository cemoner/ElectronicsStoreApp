package com.example.fooddeliveryapp.authentication.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController


@Composable
fun RegisterButton(navController: NavController,modifier: Modifier,route:String){
    Button(onClick = {navController.navigate(route)},
        modifier = modifier,
        shape = RoundedCornerShape(32.dp)

    ) {
        Text("Register",modifier= Modifier.padding(7.5.dp), fontSize = 14.sp)
    }
}