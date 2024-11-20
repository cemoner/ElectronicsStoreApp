package com.example.fooddeliveryapp.features.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.features.cart.domain.usecase.CartUseCase
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.fooddeliveryapp.features.cart.presentation.contract.CartPageContract.UiAction
import com.example.fooddeliveryapp.features.cart.presentation.contract.CartPageContract.UiState
import com.example.fooddeliveryapp.features.cart.presentation.contract.CartPageContract.SideEffect
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.navigation.model.Destination
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartPageViewModel @Inject constructor(private val cartUseCase: CartUseCase, private val appNavigator: AppNavigator):ViewModel(),
    MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

        init {
            viewModelScope.launch {
                getCartProducts()
            }
        }

    private fun calculateTotalPrice(){
        var totalPrice = 0.0
        uiState.value.products.forEach {
            if(it.salePrice != 0.0){
                totalPrice += it.salePrice
            }
            else totalPrice += it.price
        }
        updateUiState(newUiState = uiState.value.copy(totalPrice = totalPrice))
    }

    override fun onAction(action: UiAction) {
        when(action){
            is UiAction.DeleteFromCart -> onDeleteFromCart(action.productId)
            is UiAction.OnBuyClicked -> onBuyClicked()
            is UiAction.BackClicked -> onNavigateBack()
            is UiAction.OnProductClicked -> onNavigateToProductDetail(action.productId)
        }
    }

    private fun onNavigateBack() = viewModelScope.launch{
        appNavigator.tryNavigateBack()
    }

    private fun onNavigateToProductDetail(productId:Int) = viewModelScope.launch{
        appNavigator.tryNavigateTo(
            Destination.ProductDetail(productId),
            popUpToRoute = null,
            inclusive = false,
        )
    }

    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }


    private fun onBuyClicked(){
        if(IsLoggedInSingleton.getIsLoggedIn()) {
            if(uiState.value.products.isNotEmpty()){
                viewModelScope.launch {
                    clearCart(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId())
                }
            }
            else {
                onCreateToast("You don't have any items in your cart!")
            }
        }
        else {
            onCreateToast("Please login first")
        }
    }


    private fun clearCart(storeName:String,userId:String) {
        viewModelScope.launch {
            val response = cartUseCase.clearCart(storeName, userId)
            when(response) {
                200 -> {
                    onCreateToast("Cart cleared")
                    calculateTotalPrice()
                    updateUiState(newUiState = uiState.value.copy(products = emptyList()))
                }

                400 -> {
                    onCreateToast("Cart could not be cleared")
                }
            }
        }
    }

     private fun getCartProducts() = viewModelScope.launch{
        if(IsLoggedInSingleton.getIsLoggedIn()) {
            val response = cartUseCase.getCart(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId())
            updateUiState(newUiState = uiState.value.copy(products = response))
            calculateTotalPrice()
        }
    }


    private fun onDeleteFromCart(productId:Int){
        if(IsLoggedInSingleton.getIsLoggedIn()){
            viewModelScope.launch {
                val response = cartUseCase.deleteFromCart(StoreNameSingleton.getStoreName(),
                    UserIdSingleton.getUserId(),productId)
                when(response.first){
                    200 -> {
                        onCreateToast("Product deleted from cart")
                        getCartProducts()
                        calculateTotalPrice()
                    }
                    400 -> {
                        onCreateToast(response.second)
                    }
                }
            }
        }
        else {
            onCreateToast("Please login first")
        }
    }
}

private fun initialUiState():UiState = UiState(emptyList(),0.0)