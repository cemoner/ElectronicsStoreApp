package com.example.electronicsstoreapp.features.home.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.features.home.presentation.viewmodel.FavoritesPageViewModel
import com.example.electronicsstoreapp.features.home.presentation.contract.FavoritesPageContract.UiState
import com.example.electronicsstoreapp.features.home.presentation.contract.FavoritesPageContract.UiAction
import com.example.electronicsstoreapp.features.home.presentation.contract.FavoritesPageContract.SideEffect
import com.example.electronicsstoreapp.common.presentation.composable.ProductCard
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoritesPageScreen(){
    val viewModel = hiltViewModel<FavoritesPageViewModel>()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    FavoritesPageContent(uiState,onAction,sideEffect)

}

@Composable
fun FavoritesPageContent(uiState: UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>){
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = { TopBar(onAction) },
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            if(uiState.products.isEmpty()){
                item{ Text("Your Favorites List is empty.", modifier = Modifier.fillMaxSize().padding(12.dp), fontSize = 25.sp, textAlign = TextAlign.Center) }
            }
            items(uiState.products) { product ->
                ProductCard(
                    product = product,
                    onProductClicked = { productId ->
                        onAction(UiAction.OnProductClicked(productId))
                    },
                    onFavoritesClicked = { productId ->
                        onAction(UiAction.OnFavoritesButtonClicked(productId))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onAction:(UiAction) -> Unit){
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Cart",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            } },

        navigationIcon = {
            IconButton(onClick = {onAction(UiAction.OnBackButtonCLicked)})  {
                Icon(Icons.Default.Close, contentDescription = "Back", tint = Color.Gray)
            }
        },
    )
}