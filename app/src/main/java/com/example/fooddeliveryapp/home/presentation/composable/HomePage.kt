package com.example.fooddeliveryapp.home.presentation.composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.home.presentation.viewmodel.HomePageViewModel
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiState
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiAction
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.SideEffect
import com.example.fooddeliveryapp.mvi.CollectSideEffect
import com.example.fooddeliveryapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun HomePage(navController: NavController) {
    val viewModel:HomePageViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    HomePage(uiState,onAction,sideEffect,navController)
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(uiState:UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>,navController: NavController){

    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.Navigate -> {
                navController.navigate(it.route)
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(modifier = Modifier)
        SearchBar(
            query = uiState.searchText,
            onQueryChange = {onAction(UiAction.SearchTextChange(it))},
            onSearch = { onAction(UiAction.SearchTextChange(it)) } ,
            active = uiState.isSearching,
            colors = SearchBarDefaults.colors(colorResource(id = R.color.white)),
            onActiveChange = {onAction(UiAction.SearchClicked)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (uiState.searchText.isNotEmpty()) {
                    IconButton(onClick = { onAction(UiAction.SearchTextChange(""))}) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            placeholder = {Text("Search")}
        )
        {
        }
    }
}


@Composable
fun TopBar(modifier: Modifier) {
    TopAppBar(title = { Text("Welcome",
        color =Color.Black)},
        modifier = modifier,
        backgroundColor =  colorResource(id = R.color.white),
        actions = { Icon(imageVector = Icons.Default.Home, contentDescription = "Address",
            modifier = Modifier.size(48.dp).padding(5.dp), tint = Color.hsl(254f, 0.44f, 0.32f)) })
}