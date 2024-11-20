package com.example.fooddeliveryapp.features.home.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.features.home.domain.usecase.GetProductDetailUseCase
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.SideEffect
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.UiState
import com.example.fooddeliveryapp.common.presentation.mapper.toUiModel
import com.example.fooddeliveryapp.common.presentation.model.ProductUI
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailPageViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    savedStateHandle: SavedStateHandle): CommonViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    init {
        viewModelScope.launch {
            val productId = savedStateHandle.get<String>("productId")?.toIntOrNull()
                ?: throw IllegalArgumentException("Invalid or missing productId")
            val productResult = getProductDetailUseCase(StoreNameSingleton.getStoreName(),productId)
            lateinit var productUI: ProductUI
            productResult.onSuccess {
                productUI = it.toUiModel()
                updateUiState(newUiState = uiState.value.copy(product = productUI))
            }
            productResult.onFailure {
                onCreateToast(it.message!!)
            }
        }

    }

     override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.AddToCartButtonClicked -> {
                onAddToCartButtonClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId,::onCreateToast)
            }

            is UiAction.AddToFavoritesButtonClicked -> {
                onFavoritesButtonClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId,::onCreateToast)
            }

            is UiAction.OnBackButtonClicked -> tryNavigateBack()
        }
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }
}

private fun initialUiState(): UiState = UiState(
    ProductUI(
    -1,
    "Product",
    0.00,
    0.00,
    "Description",
    "Category",
    "Image Link",
    0.00
)
)