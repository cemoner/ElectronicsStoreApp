package com.example.fooddeliveryapp.home.domain.usecase

import com.example.fooddeliveryapp.home.domain.model.Product
import com.example.fooddeliveryapp.home.domain.repository.HomeRepository
import com.example.fooddeliveryapp.home.presentation.model.ProductUI
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getProducts(store:String):List<ProductUI> {
        val productList:List<Product> = homeRepository.getProducts(store)
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
}