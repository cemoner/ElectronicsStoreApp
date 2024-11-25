package com.example.electronicsstoreapp.features.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.common.presentation.mapper.toUiModel
import com.example.electronicsstoreapp.features.cart.domain.usecase.ClearCartUseCase
import com.example.electronicsstoreapp.features.cart.domain.usecase.DeleteCartItemUseCase
import com.example.electronicsstoreapp.features.cart.domain.usecase.GetCartItemsUseCase
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.SideEffect
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.UiAction
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.UiState
import com.example.electronicsstoreapp.main.util.IsLoggedInSingleton
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.main.util.UserIdSingleton
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.mvi.mvi
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartPageViewModel
    @Inject
    constructor(
        private val deleteCartItemUseCase: DeleteCartItemUseCase,
        private val getCartItemsUseCase: GetCartItemsUseCase,
        private val clearCartUseCase: ClearCartUseCase,
        private val appNavigator: AppNavigator,
    ) : ViewModel(),
        MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {
        init {
            viewModelScope.launch {
                getCartProducts()
            }
        }

        private fun calculateTotalPrice() {
            var totalPrice = 0.0
            uiState.value.products.forEach {
                if (it.salePrice != 0.0) {
                    totalPrice += it.salePrice
                } else {
                    totalPrice += it.price
                }
            }
            updateUiState(newUiState = uiState.value.copy(totalPrice = totalPrice))
        }

        override fun onAction(action: UiAction) {
            when (action) {
                is UiAction.DeleteFromCart -> onDeleteFromCart(action.productId)
                is UiAction.OnBuyClicked -> onBuyClicked()
                is UiAction.BackClicked -> onNavigateBack()
                is UiAction.OnProductClicked -> onNavigateToProductDetail(action.productId)
            }
        }

        private fun onNavigateBack() =
            viewModelScope.launch {
                appNavigator.tryNavigateBack()
            }

        private fun onNavigateToProductDetail(productId: Int) =
            viewModelScope.launch {
                appNavigator.tryNavigateTo(
                    Destination.ProductDetail(productId),
                    popUpToRoute = null,
                    inclusive = false,
                )
            }

        private fun onCreateToast(message: String) {
            viewModelScope.launch {
                emitSideEffect(SideEffect.ShowToast(message))
            }
        }

        private fun onBuyClicked() {
            if (IsLoggedInSingleton.getIsLoggedIn()) {
                if (uiState.value.products.isNotEmpty()) {
                    viewModelScope.launch {
                        clearCart(StoreNameSingleton.getStoreName(), UserIdSingleton.getUserId())
                    }
                } else {
                    onCreateToast("You don't have any items in your cart!")
                }
            } else {
                onCreateToast("Please login first")
            }
        }

        private fun clearCart(
            storeName: String,
            userId: String,
        ) {
            viewModelScope.launch {
                val result = clearCartUseCase(storeName, userId)
                result.onSuccess {
                    onCreateToast("Cart cleared")
                    getCartProducts()
                    calculateTotalPrice()
                }
                result.onFailure {
                    onCreateToast(it.message.toString())
                }
            }
        }

        private fun getCartProducts() =
            viewModelScope.launch {
                if (IsLoggedInSingleton.getIsLoggedIn()) {
                    val result = getCartItemsUseCase(StoreNameSingleton.getStoreName(), UserIdSingleton.getUserId())
                    result.onSuccess {
                        updateUiState(newUiState = uiState.value.copy(products = it.map { it1 -> it1.toUiModel() }))
                        calculateTotalPrice()
                    }
                    result.onFailure {
                        onCreateToast(it.message.toString())
                    }
                }
            }

        private fun onDeleteFromCart(productId: Int) {
            if (IsLoggedInSingleton.getIsLoggedIn()) {
                viewModelScope.launch {
                    val result =
                        deleteCartItemUseCase(
                            StoreNameSingleton.getStoreName(),
                            UserIdSingleton.getUserId(),
                            productId,
                        )
                    result.onSuccess {
                        onCreateToast("Product deleted from cart")
                        getCartProducts()
                        calculateTotalPrice()
                    }
                    result.onFailure {
                        onCreateToast(it.message.toString())
                    }
                }
            } else {
                onCreateToast("Please login first")
            }
        }
    }

private fun initialUiState(): UiState = UiState(emptyList(), 0.0)
