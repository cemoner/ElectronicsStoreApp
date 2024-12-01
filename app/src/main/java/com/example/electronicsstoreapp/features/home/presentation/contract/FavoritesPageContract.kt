package com.example.electronicsstoreapp.features.home.presentation.contract

import com.example.electronicsstoreapp.common.presentation.model.ProductUI

sealed interface FavoritesPageContract {
    data class UiState(
        val products: List<ProductUI>,
    )

    sealed interface UiAction {
        object OnBackButtonCLicked : UiAction

        data class OnFavoritesButtonClicked(
            val productId: Int,
        ) : UiAction

        data class OnAddToCartButtonClicked(
            val productId: Int,
        ) : UiAction

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
