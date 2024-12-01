package com.example.electronicsstoreapp.features.home.presentation.contract

import com.example.electronicsstoreapp.common.presentation.model.ProductUI
import com.example.electronicsstoreapp.features.home.presentation.model.CarouselItem

interface ProductDetailPageContract {
    data class UiState(
        val product: ProductUI,
        val images: List<String>,
        val carouselItems: List<CarouselItem>,
    )

    sealed interface UiAction {
        data class OnFavoritesButtonClicked(
            val productId: Int,
        ) : UiAction

        data class AddToCartButtonClicked(
            val productId: Int,
        ) : UiAction

        object OnBackButtonClicked : UiAction

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
