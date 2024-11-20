package com.example.fooddeliveryapp.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.features.home.domain.model.Product
import com.example.fooddeliveryapp.features.home.domain.usecase.AddToCartUseCase
import com.example.fooddeliveryapp.features.home.domain.usecase.AddToFavoritesUseCase
import com.example.fooddeliveryapp.features.home.domain.usecase.GetProductsUseCase
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.UiState
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.SideEffect
import com.example.fooddeliveryapp.features.home.presentation.mapper.toUiModel
import com.example.fooddeliveryapp.features.home.presentation.model.ProductUI
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val navigator: AppNavigator)
    :ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(
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
            is UiAction.SearchTextChange -> {
                onSearchTextChange(action.searchText)
            }
            is UiAction.SearchClicked -> {
                onToggleSearch()
            }
            is UiAction.ProductClicked -> {
                navigateToProductDetail(action.productId)
            }
            is UiAction.OnAddClicked -> onAddToCartClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId)
        }
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }

    private fun onAddToCartClicked(storeName:String,userId:String,productId:Int){
        if(IsLoggedInSingleton.getIsLoggedIn()){
            viewModelScope.launch {
                val addResult = addToCartUseCase(storeName, userId, productId)
                addResult.onSuccess {
                    onCreateToast(it.message!!)
                }
                addResult.onFailure {
                    onCreateToast(it.message!!)
                }
            }
        }
        else {
            onCreateToast("You need to be logged in to do this.")
        }

    }

    private fun navigateToProductDetail(productId:Int){
        navigator.tryNavigateTo(
            Destination.ProductDetail(productId),
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