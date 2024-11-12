package com.example.fooddeliveryapp.shared.navigation.presentation.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fooddeliveryapp.shared.navigation.model.NavItem

@Composable
fun BottomNavigationBar(navController: NavController) {
        BottomNavigation(backgroundColor = Color.White,
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.small
                )
               ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            NavItem.bottomValues().forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    icon = {
                        val tint = if (currentRoute == item.route) Color(0xFF6200EE) // Purple color
                        else Color.Gray
                        Icon(item.icon, contentDescription = null,modifier = Modifier.size(32.dp),tint=tint)
                    },
                    label = { Text(item.label) },
                )
            }
        }
}

@Composable
fun ShoppingCart(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("Cart") },
        shape = CircleShape,
        backgroundColor = Color.hsl(254f, 0.44f, 0.32f),
        elevation = FloatingActionButtonDefaults.elevation(2.dp,3.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart",
            tint = Color.White,
            modifier = Modifier
                .padding(24.dp)
                .size(36.dp)
        )
    }
}