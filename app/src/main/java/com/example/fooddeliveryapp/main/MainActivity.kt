package com.example.fooddeliveryapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import com.example.fooddeliveryapp.authentication.login.domain.model.IsLoggedInSingleton
import com.example.fooddeliveryapp.authentication.login.presentation.composable.Login
import com.example.fooddeliveryapp.authentication.login.presentation.composable.Register
import com.example.fooddeliveryapp.home.presentation.composable.HomePage
import com.example.fooddeliveryapp.shared.navigation.domain.model.BottomNavItem
import com.example.fooddeliveryapp.shared.navigation.presentation.composable.BottomNavigationBar
import com.example.fooddeliveryapp.shared.navigation.presentation.composable.ShoppingCart
import com.example.fooddeliveryapp.shared.profile.presentation.composable.Profile
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (shouldShowCart(currentRoute)) {
                ShoppingCart(navController = navController)
            }
        },
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
            }
            composable(BottomNavItem.Profile.route) {
                if(IsLoggedInSingleton.getIsLoggedIn()){
                    Profile()
                }
                else {
                    Login(navController)
                }
            }
            composable("Login"){ Login(navController = navController)}
            composable("Register"){ Register(navController) }
            composable("Cart"){}
        }
    }
}

@Composable
fun shouldShowCart(currentRoute: String?): Boolean {
    return when (currentRoute) {
        "Home" -> true
        "Profile" -> IsLoggedInSingleton.getIsLoggedIn()
        else -> false
    }
}