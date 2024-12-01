package com.example.electronicsstoreapp.features.home.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.common.presentation.mapper.toUiModel
import com.example.electronicsstoreapp.common.presentation.model.ProductUI
import com.example.electronicsstoreapp.features.home.domain.usecase.GetProductDetailUseCase
import com.example.electronicsstoreapp.features.home.presentation.contract.ProductDetailPageContract.SideEffect
import com.example.electronicsstoreapp.features.home.presentation.contract.ProductDetailPageContract.UiAction
import com.example.electronicsstoreapp.features.home.presentation.contract.ProductDetailPageContract.UiState
import com.example.electronicsstoreapp.features.home.presentation.model.CarouselItem
import com.example.electronicsstoreapp.main.util.GsonUtils.gson
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.main.util.UserIdSingleton
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.mvi.mvi
import com.example.electronicsstoreapp.navigation.model.Destination
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailPageViewModel
    @Inject
    constructor(
        private val getProductDetailUseCase: GetProductDetailUseCase,
        savedStateHandle: SavedStateHandle,
    ) : CommonViewModel(),
        MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {
        init {
            viewModelScope.launch {
                val productId =
                    savedStateHandle.get<String>("productId")?.toIntOrNull()
                        ?: throw IllegalArgumentException("Invalid or missing productId")

                val carouselItems: List<CarouselItem> = savedStateHandle.get<String>(Destination.ProductDetail.CAROUSEL_ITEMS_KEY)?.let { jsonString ->
                    gson.fromJson(jsonString, object : TypeToken<List<CarouselItem>>() {}.type)
                } ?: emptyList()

                val productResult =
                    getProductDetailUseCase(StoreNameSingleton.getStoreName(), productId)
                lateinit var productUI: ProductUI
                productResult.onSuccess {
                    productUI = it.toUiModel()
                    updateUiState(
                        newUiState =
                            uiState.value.copy(
                                product = productUI,
                                images = listOf(productUI.image1, productUI.image1, productUI.image1),
                                carouselItems = carouselItems
                            ),
                    )
                }
                productResult.onFailure {
                    onCreateToast(it.message!!)
                }
            }
        }

        override fun onAction(action: UiAction) {
            when (action) {
                is UiAction.AddToCartButtonClicked -> {
                    onAddToCartButtonClicked(
                        StoreNameSingleton.getStoreName(),
                        UserIdSingleton.getUserId(),
                        action.productId,
                        ::onCreateToast,
                    )
                }

                is UiAction.OnFavoritesButtonClicked -> {
                    onFavoritesButtonClicked(
                        StoreNameSingleton.getStoreName(),
                        UserIdSingleton.getUserId(),
                        action.productId,
                        ::onCreateToast,
                    )
                }

                is UiAction.OnBackButtonClicked -> tryNavigateBack()
                is UiAction.OnProductClicked -> navigateToProductDetail(action.productId,action.productCategory)
            }
        }

        private fun onCreateToast(message: String) {
            viewModelScope.launch {
                emitSideEffect(SideEffect.ShowToast(message))
            }
        }
    }

private fun initialUiState(): UiState =
    UiState(
        product =
            ProductUI(
                -1,
                "Product",
                0.00,
                0.00,
                "Description",
                "Category",
                "Image Link",
                "Image 2 Link",
                "Image 3 Link",
                0.00,
            ),
        emptyList(),
        emptyList()
    )
