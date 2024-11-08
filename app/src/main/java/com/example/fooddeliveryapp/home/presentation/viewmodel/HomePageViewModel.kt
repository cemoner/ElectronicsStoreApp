package com.example.fooddeliveryapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiState
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiAction
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.SideEffect
import com.example.fooddeliveryapp.mvi.mvi
import kotlinx.coroutines.launch

class HomePageViewModel:ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(action: UiAction) {
        when (action) {

            is UiAction.SearchTextChange -> {
                onSearchTextChange(action.searchText)
            }

            UiAction.SearchClicked -> {
                onToggleSearch()
            }
        }
    }

    fun onIsSearchingChange(boolean: Boolean){
        updateUiState(newUiState = uiState.value.copy(isSearching = boolean))
    }

    private fun onNavigateTo(destination:String) {
        viewModelScope.launch {
            emitSideEffect(SideEffect.Navigate(destination))
        }
    }

    fun onSearchTextChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(searchText = text))
    }

    fun onToggleSearch() {
        onIsSearchingChange(!uiState.value.isSearching)
        if (!uiState.value.isSearching) {
            onSearchTextChange("")
        }
    }
}
private fun initialUiState(): UiState = UiState("",false)