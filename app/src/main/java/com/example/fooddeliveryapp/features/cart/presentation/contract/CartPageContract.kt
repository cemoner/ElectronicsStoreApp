package com.example.fooddeliveryapp.features.cart.presentation.contract

import com.example.fooddeliveryapp.common.presentation.model.entity.ProductUI

sealed interface CartPageContract {
    data class UiState(
        val products:List<ProductUI>,
        val totalPrice:Double
        )

    sealed interface UiAction {
        object BackClicked: UiAction
        data class DeleteFromCart(val productId:Int): UiAction
        object OnBuyClicked: UiAction
        data class OnProductClicked(val productId: Int): UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(val message:String): SideEffect
    }
}