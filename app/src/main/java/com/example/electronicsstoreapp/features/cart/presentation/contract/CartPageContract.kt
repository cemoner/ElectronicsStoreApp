package com.example.electronicsstoreapp.features.cart.presentation.contract

import com.example.electronicsstoreapp.common.presentation.model.ProductUI

sealed interface CartPageContract {
    data class UiState(
        val products: List<ProductUI>,
        val totalPrice: Double,
    )

    sealed interface UiAction {
        object BackClicked : UiAction

        data class DeleteFromCart(
            val productId: Int,
        ) : UiAction

        object OnBuyClicked : UiAction

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
