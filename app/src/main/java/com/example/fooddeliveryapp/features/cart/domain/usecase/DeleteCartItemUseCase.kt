package com.example.fooddeliveryapp.features.cart.domain.usecase

import com.example.fooddeliveryapp.features.cart.domain.repository.CartActionRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(private val cartActionRepository: CartActionRepository) {

    suspend operator fun invoke(store: String,userId:String, productId:Int):Result<String> = cartActionRepository.deleteFromCart(store, userId,productId)

}