package com.example.fooddeliveryapp.home.presentation.composable.homepage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.home.presentation.viewmodel.HomePageViewModel
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiState
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiAction
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.SideEffect
import com.example.fooddeliveryapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun HomePageScreen() {
    val viewModel:HomePageViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    HomePageContent(uiState,onAction,sideEffect)
}




@Composable
fun HomePageContent(uiState:UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>){

    Scaffold(
        topBar = {
            TopBar()
                 },
        content = { padding ->
            Column(modifier = Modifier.padding(padding), horizontalAlignment = Alignment.CenterHorizontally){
                SearchBar(uiState,onAction)
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                ) {
                    items(uiState.products){ product ->
                        ProductCard(product,onAction)
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(uiState:UiState, onAction: (UiAction) -> Unit){
    SearchBar(
        query = uiState.searchText,
        onQueryChange = {onAction(UiAction.SearchTextChange(it))},
        onSearch = { onAction(UiAction.SearchTextChange(it)) } ,
        active = uiState.isSearching,
        colors = SearchBarDefaults.colors(colorResource(R.color.white)),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(title = { Text("Welcome",
        color =Color.Black)},
        modifier = Modifier.padding(bottom = 12.dp),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(R.color.white)),
        actions = { Icon(imageVector = Icons.Default.Home, contentDescription = "Address",
            modifier = Modifier.size(48.dp).padding(5.dp), tint = Color.hsl(254f, 0.44f, 0.32f)) })
}