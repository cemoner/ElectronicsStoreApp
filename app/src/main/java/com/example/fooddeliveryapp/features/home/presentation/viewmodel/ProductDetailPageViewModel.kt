package com.example.fooddeliveryapp.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.features.home.domain.usecase.AddToCartUseCase
import com.example.fooddeliveryapp.features.home.domain.usecase.AddToFavoritesUseCase
import com.example.fooddeliveryapp.features.home.domain.usecase.GetProductDetailUseCase
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.SideEffect
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.UiState
import com.example.fooddeliveryapp.features.home.presentation.mapper.toUiModel
import com.example.fooddeliveryapp.features.home.presentation.model.ProductUI
import com.example.fooddeliveryapp.main.util.IsLoggedInSingleton
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailPageViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val navigator: AppNavigator, savedStateHandle: SavedStateHandle):
    ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

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
        }

    }


     override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.AddToCartClicked -> {
                onAddToCartClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId)
            }

            is UiAction.AddToFavoritesClicked -> {
                onAddToFavoritesClicked(StoreNameSingleton.getStoreName(),UserIdSingleton.getUserId(),action.productId)
            }

            is UiAction.BackClicked -> {
                viewModelScope.launch {
                    navigator.tryNavigateBack()
                }
            }

        }
    }


    private fun onAddToCartClicked(storeName:String,userId:String,productId:Int) = viewModelScope.launch{
        Log.d("userId", UserIdSingleton.getUserId())
        if(IsLoggedInSingleton.getIsLoggedIn()){
            val result = addToCartUseCase(storeName,userId,productId)
            result.onSuccess {
                onCreateToast(it.message!!)
            }
            result.onFailure {
                onCreateToast(it.message!!)
            }
        }
        else {
            onCreateToast("You need to be logged in to do this.")
        }

    }

    private fun onAddToFavoritesClicked(storeName:String,userId:String,productId:Int) = viewModelScope.launch{
        if(IsLoggedInSingleton.getIsLoggedIn()){
            val result = addToFavoritesUseCase(storeName,userId,productId)
            result.onSuccess {
                onCreateToast(it.message!!)
            }
            result.onFailure {
                onCreateToast(it.message!!)
            }
        }
        else {
            onCreateToast("You need to be logged in to do this.")
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