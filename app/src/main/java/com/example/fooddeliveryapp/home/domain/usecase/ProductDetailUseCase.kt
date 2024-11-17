package com.example.fooddeliveryapp.home.domain.usecase

import com.example.fooddeliveryapp.home.data.model.request.AddRequest
import com.example.fooddeliveryapp.home.domain.model.AddResult
import com.example.fooddeliveryapp.home.domain.repository.ProductDetailRepository
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor
    (
    private val productDetailRepository: ProductDetailRepository
) {

    suspend fun addToCart(store:String,addRequest: AddRequest):AddResult = productDetailRepository.addToCart(store,addRequest)
    suspend fun addToFavorites(store:String,addRequest: AddRequest):AddResult = productDetailRepository.addToFavorites(store,addRequest)

}