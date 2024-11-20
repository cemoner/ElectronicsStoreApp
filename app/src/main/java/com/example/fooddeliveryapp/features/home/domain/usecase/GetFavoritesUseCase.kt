package com.example.fooddeliveryapp.features.home.domain.usecase

import com.example.fooddeliveryapp.features.home.domain.repository.ProductDataRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val productDataRepository: ProductDataRepository){
    suspend operator fun invoke(store:String,userId:String) = productDataRepository.getFavorites(store,userId)
}