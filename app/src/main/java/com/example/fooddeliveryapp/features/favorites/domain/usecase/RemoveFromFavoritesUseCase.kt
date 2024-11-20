package com.example.fooddeliveryapp.features.favorites.domain.usecase

import com.example.fooddeliveryapp.features.favorites.domain.repository.FavoritesActionRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(private val favoritesActionRepository: FavoritesActionRepository) {
    suspend operator fun invoke(store:String,userId:String,productId:Int) = favoritesActionRepository.removeFromFavorites(store,userId,productId)
}