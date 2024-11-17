package com.example.fooddeliveryapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.home.domain.usecase.HomeUseCase
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiState
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.UiAction
import com.example.fooddeliveryapp.home.presentation.contracts.HomePageContract.SideEffect
import com.example.fooddeliveryapp.home.presentation.model.ProductUI
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val homeUseCase: HomeUseCase, private val navigator: AppNavigator):ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    init {
        viewModelScope.launch {
            updateUiState(newUiState = uiState.value.copy(products = homeUseCase.getProducts(StoreNameSingleton.getStoreName())))
        }
    }

    override fun onAction(action: UiAction) {
        when (action) {

            is UiAction.SearchTextChange -> {
                onSearchTextChange(action.searchText)
            }

            UiAction.SearchClicked -> {
                onToggleSearch()
            }

            is UiAction.ProductClicked -> {
                viewModelScope.launch {
                    for(product in uiState.value.products){
                        if(product.id == action.productId){
                            navigateToProductDetail(product)
                        }
                    }
                }
            }
        }
    }


    private fun navigateToProductDetail(product:ProductUI){
        navigator.tryNavigateTo(
            Destination.ProductDetail(product),
            popUpToRoute = null,
            inclusive = false,
        )
    }

    fun onIsSearchingChange(boolean: Boolean){
        updateUiState(newUiState = uiState.value.copy(isSearching = boolean))
    }


    fun onSearchTextChange(text: String) {
        updateUiState(newUiState = uiState.value.copy(searchText = text))
    }

    fun onToggleSearch() {
        onIsSearchingChange(!uiState.value.isSearching)
        if (!uiState.value.isSearching) {
            onSearchTextChange("")
        }
    }
}
private fun initialUiState(): UiState = UiState("",false, emptyList())