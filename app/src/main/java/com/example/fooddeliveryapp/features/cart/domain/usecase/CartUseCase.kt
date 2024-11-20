package com.example.fooddeliveryapp.features.cart.domain.usecase

import com.example.fooddeliveryapp.features.cart.domain.model.Product
import com.example.fooddeliveryapp.features.cart.domain.repository.CartRepository
import com.example.fooddeliveryapp.features.home.presentation.model.ProductUI
import javax.inject.Inject

class CartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend fun getCart(store:String, userId:String):List<ProductUI> {

        val productList:List<Product> = cartRepository.getCart(store, userId)
        val productUIList = mutableListOf<ProductUI>()
        for(product in productList){
            val productUI = ProductUI(
                product.id,
                product.title,
                product.price,
                product.salePrice,
                product.description,
                product.category,
                product.imageOne,
                product.rate
            )
            productUIList.add(productUI)
        }
        return productUIList
    }
    suspend fun deleteFromCart(store: String,userId:String, productId:Int):Pair<Int,String> = cartRepository.deleteFromCart(store, userId,productId)
    suspend fun clearCart(store: String,userId:String):Int = cartRepository.clearCart(store, userId)


}