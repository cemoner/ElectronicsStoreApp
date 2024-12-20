package com.example.electronicsstoreapp.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicsstoreapp.common.presentation.mapper.toUiModel
import com.example.electronicsstoreapp.features.home.domain.usecase.AddToCartUseCase
import com.example.electronicsstoreapp.features.home.domain.usecase.AddToFavoritesUseCase
import com.example.electronicsstoreapp.features.home.domain.usecase.DeleteFromFavoritesUseCase
import com.example.electronicsstoreapp.features.home.domain.usecase.SearchProductUseCase
import com.example.electronicsstoreapp.features.home.presentation.mapper.toCarouselItem
import com.example.electronicsstoreapp.features.home.presentation.model.CarouselItem
import com.example.electronicsstoreapp.main.util.FavoritesSingleton
import com.example.electronicsstoreapp.main.util.IsLoggedInSingleton
import com.example.electronicsstoreapp.main.util.StoreNameSingleton
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.navigator.AppNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

open class CommonViewModel : ViewModel() {

    @Inject
    lateinit var addToCartUseCase: AddToCartUseCase

    @Inject
    lateinit var addToFavoritesUseCase: AddToFavoritesUseCase

    @Inject
    lateinit var deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var searchProductsUseCase: SearchProductUseCase

    fun onAddToCartButtonClicked(
        storeName: String,
        userId: String,
        productId: Int,
        onCreateToast: (String) -> Unit,
    ) {
        if (IsLoggedInSingleton.getIsLoggedIn()) {
            viewModelScope.launch {
                val addResult = addToCartUseCase(storeName, userId, productId)
                addResult.onSuccess {
                    onCreateToast(it.message!!)
                }
                addResult.onFailure {
                    onCreateToast(it.message.toString())
                }
            }
        } else {
            onCreateToast("Please login to add to cart")
        }
    }

    fun navigateToProductDetail(productId: Int,productCategory:String) {
        lateinit var carouselItems:List<CarouselItem>
        viewModelScope.launch {
            val searchResult = searchProductsUseCase(StoreNameSingleton.getStoreName(),productCategory)
            searchResult.onSuccess {
                val productUI = it.map { it1 -> it1.toUiModel() }.filter { it1 -> it1.id != productId }
                 carouselItems = productUI.map { it.toCarouselItem() }
                navigator.tryNavigateTo(
                    Destination.ProductDetail(productId,carouselItems),
                    popUpToRoute = null,
                    inclusive = false,
                )
            }
        }
    }

    fun onFavoritesButtonClicked(
        storeName: String,
        userId: String,
        productId: Int,
        onCreateToast: (String) -> Unit,
    ) = viewModelScope.launch {
        if (IsLoggedInSingleton.getIsLoggedIn()) {
            if (!FavoritesSingleton.isFavorite(productId)) {
                val result = addToFavoritesUseCase(storeName, userId, productId)
                result.onSuccess {
                    FavoritesSingleton.addToFavorites(productId)
                    onCreateToast(it.message!!)
                }
                result.onFailure {
                    onCreateToast(it.message!!)
                }
            } else {
                val result = deleteFromFavoritesUseCase(storeName, userId, productId)
                result.onSuccess {
                    onCreateToast(it.message!!)
                    FavoritesSingleton.deleteFromFavorites(productId)
                }
                result.onFailure {
                    onCreateToast(it.message!!)
                }
            }
        } else {
            onCreateToast("You need to be logged in to do this.")
        }
    }

    fun tryNavigateBack() {
        viewModelScope.launch {
            navigator.tryNavigateBack()
        }
    }
}
