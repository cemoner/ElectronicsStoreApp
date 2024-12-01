package com.example.electronicsstoreapp.features.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.common.presentation.mapper.toUiModel
import com.example.electronicsstoreapp.features.home.domain.usecase.GetFavoritesUseCase
import com.example.electronicsstoreapp.features.home.presentation.contract.FavoritesPageContract.SideEffect
import com.example.electronicsstoreapp.features.home.presentation.contract.FavoritesPageContract.UiAction
import com.example.electronicsstoreapp.features.home.presentation.contract.FavoritesPageContract.UiState
import com.example.electronicsstoreapp.main.util.IsLoggedInSingleton
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.main.util.UserIdSingleton
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesPageViewModel
    @Inject
    constructor(
        private val getFavoritesUseCase: GetFavoritesUseCase,
    ) : CommonViewModel(),
        MVI<UiState, UiAction, SideEffect> by mvi(
            initialUiState(),
        ) {
        init {
            getFavorites(StoreNameSingleton.getStoreName(), UserIdSingleton.getUserId())
        }

        private fun onCreateToast(message: String) {
            viewModelScope.launch {
                emitSideEffect(SideEffect.ShowToast(message))
            }
        }

        override fun onAction(action: UiAction) {
            when (action) {
                is UiAction.OnAddToCartButtonClicked ->
                    onAddToCartButtonClicked(
                        StoreNameSingleton.getStoreName(),
                        UserIdSingleton.getUserId(),
                        action.productId,
                        ::onCreateToast,
                    )
                is UiAction.OnBackButtonCLicked -> tryNavigateBack()
                is UiAction.OnFavoritesButtonClicked -> {
                        onFavoritesButtonClicked(
                            StoreNameSingleton.getStoreName(),
                            UserIdSingleton.getUserId(),
                            action.productId,
                            ::onCreateToast,
                        )
                        getFavorites(StoreNameSingleton.getStoreName(), UserIdSingleton.getUserId())
                    }
                is UiAction.OnProductClicked -> navigateToProductDetail(action.productId,action.productCategory)
            }
        }

        private fun getFavorites(
            store: String,
            userId: String,
        ) = viewModelScope.launch {
            if (IsLoggedInSingleton.getIsLoggedIn()) {
                val result = getFavoritesUseCase(store, userId)

                result.onSuccess {
                    updateUiState(newUiState = uiState.value.copy(products = it.map { it1 -> it1.toUiModel() }))
                }
                result.onFailure {
                    onCreateToast(it.message.toString())
                }
            }
        }
    }

private fun initialUiState(): UiState = UiState(emptyList())
