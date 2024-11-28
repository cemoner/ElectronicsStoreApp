package com.example.fooddeliveryapp.home.presentation.contracts

import com.example.fooddeliveryapp.home.presentation.model.ProductUI


interface HomePageContract {
    data class UiState(val searchText: String, val isSearching:Boolean,val products:List<ProductUI>,)

    sealed interface UiAction {
        data class SearchTextChange(val searchText: String) : UiAction
        object SearchClicked : UiAction
        data class ProductClicked(val productId:Int) : UiAction
    }

    sealed interface SideEffect {
        data class ShowToast(val message:String) : SideEffect
    }
}