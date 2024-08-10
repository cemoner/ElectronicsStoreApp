package com.example.fooddeliveryapp.shared.navigation.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

public sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Search : BottomNavItem("favorite", Icons.Default.Favorite, "Favorites")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
    object Cart : BottomNavItem("cart", Icons.Default.ShoppingCart, "Cart")

    companion object {
        fun values(): List<BottomNavItem> {
            return listOf(Home, Search, Profile)
        }
    }
}