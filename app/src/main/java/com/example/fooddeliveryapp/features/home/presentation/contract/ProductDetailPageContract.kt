package com.example.fooddeliveryapp.features.home.presentation.contract

import com.example.fooddeliveryapp.common.presentation.model.entity.ProductUI

interface ProductDetailPageContract {
    data class UiState(val product: ProductUI)

    sealed interface UiAction {
        data class AddToFavoritesClicked(val productId:Int): UiAction
        data class AddToCartClicked(val productId:Int): UiAction
        object BackClicked: UiAction
    }
    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
    }
}