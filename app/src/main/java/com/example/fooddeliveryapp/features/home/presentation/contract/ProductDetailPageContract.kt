package com.example.fooddeliveryapp.features.home.presentation.contract

import com.example.fooddeliveryapp.common.presentation.model.ProductUI

interface ProductDetailPageContract {
    data class UiState(val product: ProductUI)

    sealed interface UiAction {
        data class AddToFavoritesButtonClicked(val productId:Int): UiAction
        data class AddToCartButtonClicked(val productId:Int): UiAction
        object OnBackButtonClicked: UiAction
    }
    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
    }
}