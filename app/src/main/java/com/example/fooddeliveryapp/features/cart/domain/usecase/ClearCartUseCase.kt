package com.example.fooddeliveryapp.features.cart.domain.usecase

import com.example.fooddeliveryapp.features.cart.domain.repository.CartActionRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(private val cartActionRepository: CartActionRepository){
    suspend operator fun invoke(store: String,userId:String):Result<String> = cartActionRepository.clearCart(store, userId)
}