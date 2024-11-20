package com.example.fooddeliveryapp.features.favorites.presentation.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fooddeliveryapp.features.favorites.presentation.viewmodel.FavoritesPageViewModel
import com.example.fooddeliveryapp.features.favorites.presentation.contract.FavoritesPageContract.UiState
import com.example.fooddeliveryapp.features.favorites.presentation.contract.FavoritesPageContract.UiAction
import com.example.fooddeliveryapp.features.favorites.presentation.contract.FavoritesPageContract.SideEffect
import com.example.fooddeliveryapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoritesPageScreen(){
    val viewModel = hiltViewModel<FavoritesPageViewModel>()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    FavoritesPageContent(uiState,onAction,sideEffect)

}

@Composable
fun FavoritesPageContent(uiState: UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>){

}