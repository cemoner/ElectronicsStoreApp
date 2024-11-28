package com.example.fooddeliveryapp.features.cart.domain.usecase

import com.example.fooddeliveryapp.features.cart.domain.repository.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(store: String,userId:String, productId:Int):Result<String> = cartRepository.deleteFromCart(store, userId,productId)

}