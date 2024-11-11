package com.example.fooddeliveryapp.home.presentation.contracts


interface HomePageContract {
    data class UiState(val searchText: String, val isSearching:Boolean)

    sealed interface UiAction {
        data class SearchTextChange(val searchText: String) : UiAction
        object SearchClicked : UiAction
    }

    sealed interface SideEffect {
        data class Navigate(val route:String) : SideEffect

    }
}