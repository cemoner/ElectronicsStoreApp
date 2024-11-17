package com.example.fooddeliveryapp.navigation.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.model.NavItem
import com.example.fooddeliveryapp.navigation.presentation.viewmodel.BottomNavigationViewModel

@Composable
fun NavigationBar(navController:NavHostController) {
    val viewModel: BottomNavigationViewModel = hiltViewModel()
    val items = NavItem.navigationBarItems()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route



    NavigationBar(containerColor = colorResource(id = R.color.white)) {
        items.forEach{ item->
            NavigationBarItem(
                icon = {
                    val isSelected = currentRoute?.startsWith(item.label, ignoreCase = true) == true
                            || (currentRoute == null && item.label == Destination.Home())
                    val icon = if (isSelected) item.selectedIcon else item.unselectedIcon
                    Icon(imageVector = icon, contentDescription = item.label)
                },

                onClick = {
                    viewModel.navigation(item.label)
                          },
                selected = currentRoute?.startsWith(item.label, ignoreCase = true) == true
            )
        }
    }
}


@Composable
fun ShoppingCart() {
    FloatingActionButton(
        onClick = {  },
        shape = CircleShape,
        containerColor = colorResource(R.color.purple_500),
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