package com.example.fooddeliveryapp.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.authentication.login.presentation.composable.Login
import com.example.fooddeliveryapp.home.presentation.composable.HomePage
import com.example.fooddeliveryapp.home.presentation.composable.TopBar
import com.example.fooddeliveryapp.shared.navigation.domain.model.BottomNavItem
import com.example.fooddeliveryapp.shared.navigation.presentation.composable.BottomNavigationBar
import com.example.fooddeliveryapp.shared.navigation.presentation.composable.ShoppingCart
import com.example.fooddeliveryapp.shared.profile.presentation.composable.Profile
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryAppTheme {
                App(Modifier)
            }
        }
    }
}

@Composable
fun App(modifier: Modifier) {
    val navController = rememberNavController()

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { ShoppingCart() },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomePage(modifier = modifier, navController = navController )}
            composable(BottomNavItem.Favorites.route) {
                if(IsLoggedInSingleton.getIsLoggedIn()){
                    Profile()
                }
                else {
                    Login()
                }
            }
            composable(BottomNavItem.Profile.route) { /* Profile Screen UI */ }
        }
    }
}