package com.example.electronicsstoreapp.features.cart.domain.usecase

import com.example.electronicsstoreapp.common.domain.model.Product
import com.example.electronicsstoreapp.features.cart.domain.repository.CartDataRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(private val cartDataRepository: CartDataRepository){
    suspend operator fun invoke(store:String, userId:String):Result<List<Product>> = cartDataRepository.getCart(store, userId)
}