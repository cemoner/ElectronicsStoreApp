package com.example.fooddeliveryapp.features.cart.domain.usecase

import com.example.fooddeliveryapp.common.domain.model.entity.Product
import com.example.fooddeliveryapp.features.cart.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(private val cartRepository: CartRepository){
    suspend operator fun invoke(store:String, userId:String):Result<List<Product>> = cartRepository.getCart(store, userId)
}