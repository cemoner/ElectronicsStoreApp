package com.example.fooddeliveryapp.features.home.domain.usecase

import com.example.fooddeliveryapp.common.domain.model.entity.Product
import com.example.fooddeliveryapp.features.home.domain.repository.ProductDataRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val productDataRepository: ProductDataRepository){

    suspend operator fun invoke(store:String,productId:Int):Result<Product> = productDataRepository.getProductDetail(store,productId)
}