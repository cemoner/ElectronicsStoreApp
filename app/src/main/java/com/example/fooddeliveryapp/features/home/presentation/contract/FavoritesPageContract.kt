package com.example.fooddeliveryapp.features.home.presentation.contract

import com.example.fooddeliveryapp.common.presentation.model.ProductUI

sealed interface FavoritesPageContract {
    data class UiState(val products:List<ProductUI>)

    sealed interface UiAction{
        object OnBackButtonCLicked: UiAction
        data class OnFavoritesButtonClicked(val productId:Int): UiAction
        data class OnAddToCartButtonClicked(val productId:Int): UiAction
        data class OnProductClicked(val productId:Int): UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(val message:String): SideEffect

    }
}