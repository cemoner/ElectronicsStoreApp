package com.example.fooddeliveryapp.features.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.common.domain.model.Product
import com.example.fooddeliveryapp.features.home.domain.usecase.GetProductsUseCase
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.UiState
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.SideEffect
import com.example.fooddeliveryapp.common.presentation.mapper.toUiModel
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase)
    : CommonViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(
    initialUiState()
) {

    init {
        viewModelScope.launch {
            val result = getProductsUseCase(StoreNameSingleton.getStoreName())
            result.onSuccess {
                val productUIList = it.map(Product::toUiModel)
                updateUiState(newUiState = uiState.value.copy(products = productUIList))
            }
            result.onFailure {
                onCreateToast(it.message!!)
            }
        }
    }

    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.OnSearchTextChange -> {
                onSearchTextChange(action.searchText)
            }
            is UiAction.OnSearchBarClicked -> {
                onToggleSearch()
            }
            is UiAction.OnProductClicked -> {
                navigateToProductDetail(action.productId)
            }
            is UiAction.OnAddToCartButtonClicked -> onAddToCartButtonClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId,::onCreateToast)
            is UiAction.OnFavoritesButtonClicked -> TODO()
        }
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
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