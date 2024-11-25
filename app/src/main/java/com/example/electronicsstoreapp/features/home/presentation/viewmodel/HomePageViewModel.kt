package com.example.electronicsstoreapp.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.features.home.domain.usecase.GetProductsUseCase
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract.UiState
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract.UiAction
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract.SideEffect
import com.example.electronicsstoreapp.common.presentation.mapper.toUiModel
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageToolBarUiState
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.main.util.UserIdSingleton
import com.example.electronicsstoreapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase)
    : CommonViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(
    initialUiState()
) {

    init {
        getProducts()
    }

    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.OnSearchTextChange -> {
                onSearchTextChange(action.searchText)
            }
            is UiAction.OnToolBarStateChange -> {
                onToolBarStateChange()

            }
            is UiAction.OnProductClicked -> {
                navigateToProductDetail(action.productId)
            }
            is UiAction.OnFavoritesButtonClicked -> onFavoritesButtonClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId,::onCreateToast)
        }
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }

    private fun getProducts() = viewModelScope.launch {

        updateUiState(UiState.Loading)

        val result = withContext(Dispatchers.IO) {
            getProductsUseCase(StoreNameSingleton.getStoreName())
        }

        result.onSuccess {
            val productUIList = it.map { product ->
                product.toUiModel().copy(isLoading = false)
            }

            updateUiState(UiState.Success(
                toolBarUiState = HomePageToolBarUiState.DefaultToolBar,
                allProducts = productUIList,
                searchProducts = productUIList
            ))
        }

        result.onFailure {
            updateUiState(UiState.Error)
            onCreateToast(it.message ?: "An error occurred")
        }
    }

    fun onSearchTextChange(text: String) {
        viewModelScope.launch {
            val currentState = (uiState.value) as? UiState.Success ?: return@launch
            val toolBarUiState = currentState.toolBarUiState as? HomePageToolBarUiState.Search ?: return@launch
            val filteredProducts = currentState.allProducts.filter { it.title.contains(text, ignoreCase = true) || it.category.contains(text,ignoreCase = true)}
            Log.e("filteredProducts",filteredProducts.toString())
            val newToolBarUiState = toolBarUiState.copy(searchText = text)
            updateUiState(currentState.copy(searchProducts = filteredProducts,toolBarUiState = newToolBarUiState))
        }
    }

    fun onToolBarStateChange(){
        val currentState = (uiState.value) as? UiState.Success ?: return

        val newToolBarUiState = when(currentState.toolBarUiState){
            is HomePageToolBarUiState.DefaultToolBar -> HomePageToolBarUiState.Search("")
            is HomePageToolBarUiState.Search -> HomePageToolBarUiState.DefaultToolBar
        }
        updateUiState(currentState.copy(searchProducts = currentState.allProducts,toolBarUiState = newToolBarUiState))
    }
}
private fun initialUiState(): UiState = UiState.Loading