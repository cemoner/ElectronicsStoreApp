package com.example.electronicsstoreapp.features.home.domain.usecase

import com.example.electronicsstoreapp.features.home.domain.model.ActionResult
import com.example.electronicsstoreapp.features.home.domain.repository.ProductActionRepository
import javax.inject.Inject

class AddToCartUseCase@Inject constructor(private val productActionRepository: ProductActionRepository,) {
    suspend operator fun invoke(store:String,userId:String,productId:Int): Result<ActionResult> = productActionRepository.addToCart(store,userId,productId)
}