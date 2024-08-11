package com.example.fooddeliveryapp.home.presentation.composable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.fooddeliveryapp.home.presentation.viewmodel.HomePageViewModel

@Composable
fun HomePage(modifier: Modifier,navController: NavController){
    val viewModel = HomePageViewModel()

    ConstraintLayout {
        val (searchBar,topAppBar) = createRefs()


        TopBar(modifier.constrainAs(topAppBar){
            top.linkTo(parent.top, margin = 16.dp)
        })
        HomePageSearchBar(viewModel = viewModel,modifier.constrainAs(searchBar){
            top.linkTo(topAppBar.bottom)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        })

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageSearchBar(viewModel: HomePageViewModel,modifier: Modifier){
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    SearchBar(
        query = searchText,
        onQueryChange = viewModel::onSearchTextChange,
        onSearch =viewModel::onSearchTextChange ,
        active = isSearching,
        modifier = modifier,
        onActiveChange = {viewModel.onToogleSearch()},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { viewModel.onSearchTextChange("") }) {
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

@Composable
fun TopBar(modifier: Modifier) {
    TopAppBar(title = { Text("Welcome",
        color = androidx.compose.ui.graphics.Color.White)},
        modifier = modifier,
        backgroundColor =  androidx.compose.ui.graphics.Color.hsl(254f, 0.44f, 0.32f),
        actions = { Icon(imageVector = Icons.Default.Home, contentDescription = "Address",
            modifier = Modifier.size(48.dp).padding(5.dp), tint = androidx.compose.ui.graphics.Color.White) })
}