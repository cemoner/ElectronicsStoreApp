package com.example.electronicsstoreapp.features.home.presentation.contract

import com.example.electronicsstoreapp.common.presentation.model.ProductUI

interface HomePageContract {
    sealed interface UiState {
        data class Success(
            val toolBarUiState: HomePageToolBarUiState,
            val allProducts: List<ProductUI>,
            val searchProducts: List<ProductUI>,
        ) : UiState

        object Loading : UiState

        object Error : UiState
    }
    sealed interface UiAction {
        data class OnSearchTextChange(
            val searchText: String,
        ) : UiAction

        data class OnFavoritesButtonClicked(
            val productId: Int,
        ) : UiAction

        object OnToolBarStateChange : UiAction

        data class OnProductClicked(
            val productId: Int,
            val productCategory:String
        ) : UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(
            val message: String,
        ) : SideEffect
    }
}
sealed interface HomePageToolBarUiState {
    data object DefaultToolBar : HomePageToolBarUiState

    data class Search(
        val searchText: String,
    ) : HomePageToolBarUiState
}
