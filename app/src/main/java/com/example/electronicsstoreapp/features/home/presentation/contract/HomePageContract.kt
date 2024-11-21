package com.example.electronicsstoreapp.features.home.presentation.contract

import com.example.electronicsstoreapp.common.presentation.model.ProductUI


interface HomePageContract {
    data class UiState(val searchText: String, val isSearching:Boolean, val products:List<ProductUI>,)

    sealed interface UiAction {
        data class OnSearchTextChange(val searchText: String) : UiAction
        data class OnFavoritesButtonClicked(val productId:Int): UiAction
        object OnSearchBarClicked : UiAction
        data class OnProductClicked(val productId: Int) : UiAction
        data class OnAddToCartButtonClicked(val productId: Int): UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
    }
}