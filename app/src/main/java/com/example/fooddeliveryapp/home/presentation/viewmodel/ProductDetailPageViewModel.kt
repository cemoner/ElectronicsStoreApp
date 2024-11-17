package com.example.fooddeliveryapp.home.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.home.domain.usecase.ProductDetailUseCase
import com.example.fooddeliveryapp.home.presentation.contracts.ProductDetailPageContract.UiState
import com.example.fooddeliveryapp.home.presentation.contracts.ProductDetailPageContract.UiAction
import com.example.fooddeliveryapp.home.presentation.contracts.ProductDetailPageContract.SideEffect
import com.example.fooddeliveryapp.home.presentation.model.ProductUI
import com.example.fooddeliveryapp.main.util.StoreNameSingleton
import com.example.fooddeliveryapp.main.util.UserIdSingleton
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailPageViewModel @Inject constructor(private val productDetailUseCase: ProductDetailUseCase,private val navigator: AppNavigator,savedStateHandle: SavedStateHandle):
    ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    init {
        val id = savedStateHandle.get<String>("id")?.toIntOrNull() ?: 0 // Safely convert to Int
        val title = savedStateHandle.get<String>("title") ?: ""
        val price = savedStateHandle.get<String>("price")?.toDoubleOrNull() ?: 0.0
        val salePrice = savedStateHandle.get<String>("salePrice")?.toDoubleOrNull() ?: 0.0
        val description = savedStateHandle.get<String>("description") ?: ""
        val category = savedStateHandle.get<String>("category") ?: ""
        val image = savedStateHandle.get<String>("image") ?: ""
        val rate = savedStateHandle.get<String>("rate")?.toDoubleOrNull() ?: 0.0

        val product = ProductUI(
            id = id,
            title = title,
            price = price,
            salePrice = salePrice,
            description = description,
            category = category,
            image = image,
            rate = rate
        )

        updateUiState(newUiState = uiState.value.copy(product = product))
    }


    override fun onAction(action: UiAction) {
        when (action) {
            UiAction.AddToCartClicked -> {
                if (UserIdSingleton.getUserId() != "-1") {
                    viewModelScope.launch {
                        val addResult = productDetailUseCase.addToCart(
                            StoreNameSingleton.getStoreName(), AddRequest(
                                UserIdSingleton.getUserId(), uiState.value.product!!.id.toString()
                            )
                        )
                        if(addResult.message != null){
                            onCreateToast(addResult.message)
                        }
                    }
                }
            }

            UiAction.AddToFavoritesClicked -> {
                if (UserIdSingleton.getUserId() != "-1") {
                    viewModelScope.launch {
                        val addResult = productDetailUseCase.addToFavorites(
                            StoreNameSingleton.getStoreName(), AddRequest(
                                UserIdSingleton.getUserId(),
                                uiState.value.product!!.id.toString()
                            )
                        )
                        if(addResult.message != null){
                            onCreateToast(addResult.message)
                        }
                    }
                }
            }

            UiAction.BackClicked -> {
                viewModelScope.launch {
                    navigator.tryNavigateBack()
                }
            }

            UiAction.OnDecrement -> onCountChange(UiAction.OnDecrement)
            UiAction.OnIncrement -> onCountChange(UiAction.OnIncrement)
        }
    }


    private fun onCountChange(uiAction: UiAction){
        if(uiAction == UiAction.OnIncrement){
            updateUiState(newUiState = uiState.value.copy(count = uiState.value.count + 1))
        }
        else {
            if(uiState.value.count >= 1){
                updateUiState(newUiState = uiState.value.copy(count = uiState.value.count -1))
            }
        }


    }




    private fun onCreateToast(message:String){
        viewModelScope.launch {
            emitSideEffect(SideEffect.ShowToast(message))
        }
    }
}



private fun initialUiState(): UiState = UiState(null,0)