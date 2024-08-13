package com.example.fooddeliveryapp.shared.navigation.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "Home")
    object Favorites : BottomNavItem("Favorites", Icons.Default.Favorite, "Favorites")
    object Profile : BottomNavItem("Profile", Icons.Default.Person, "Profile")

    companion object {
        fun values(): List<BottomNavItem> {
            return listOf(Home, Favorites, Profile)
        }
    }
}