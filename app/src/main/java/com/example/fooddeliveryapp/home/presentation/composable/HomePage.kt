package com.example.fooddeliveryapp.home.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomePage(modifier: Modifier,navController: NavController){
    Text(text = "Hello to Food Delivery App")
}
