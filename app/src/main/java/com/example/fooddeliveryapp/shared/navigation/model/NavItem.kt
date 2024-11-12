package com.example.fooddeliveryapp.shared.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : NavItem("home", Icons.Default.Home, "Home")
    object Favorites : NavItem("favorites", Icons.Default.Favorite, "Favorites")
    object Profile : NavItem("profile/{userId}", Icons.Default.Person, "Profile")
    object Login : NavItem("login", Icons.AutoMirrored.Filled.Login, "Login")
    object Register: NavItem("register", Icons.Default.HowToReg, "Register")
    object Cart: NavItem("cart", Icons.Default.ShoppingCart, "Cart")

    companion object {
        fun bottomValues(): List<NavItem> {
            return listOf(Home, Favorites, Profile)
        }
    }
}