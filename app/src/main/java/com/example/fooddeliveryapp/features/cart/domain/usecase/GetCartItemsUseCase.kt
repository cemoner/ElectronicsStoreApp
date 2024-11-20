package com.example.fooddeliveryapp.features.cart.domain.usecase

import com.example.fooddeliveryapp.common.domain.model.entity.Product
import com.example.fooddeliveryapp.features.cart.domain.repository.CartActionRepository
import com.example.fooddeliveryapp.features.cart.domain.repository.CartDataRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(private val cartDataRepository: CartDataRepository){
    suspend operator fun invoke(store:String, userId:String):Result<List<Product>> = cartDataRepository.getCart(store, userId)
}