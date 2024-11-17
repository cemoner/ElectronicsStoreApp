package com.example.fooddeliveryapp.home.presentation.contracts

import com.example.fooddeliveryapp.home.presentation.model.ProductUI

interface ProductDetailPageContract {
    data class UiState(val product:ProductUI?,val count:Int)

    sealed interface UiAction {
        object AddToFavoritesClicked:UiAction
        object AddToCartClicked:UiAction
        object BackClicked:UiAction
        object OnIncrement:UiAction
        object OnDecrement:UiAction
    }
    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
    }
}