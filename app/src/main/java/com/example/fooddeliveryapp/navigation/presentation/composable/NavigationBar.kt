package com.example.fooddeliveryapp.navigation.presentation.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
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

