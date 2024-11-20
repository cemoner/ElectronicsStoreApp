package com.example.fooddeliveryapp.features.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.features.favorites.presentation.contract.FavoritesPageContract.UiState
import com.example.fooddeliveryapp.features.favorites.presentation.contract.FavoritesPageContract.UiAction
import com.example.fooddeliveryapp.features.favorites.presentation.contract.FavoritesPageContract.SideEffect

import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesPageViewModel @Inject constructor():ViewModel(),
    MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {
}


private fun initialUiState():UiState = UiState(emptyList())