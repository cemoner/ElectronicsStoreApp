package com.example.fooddeliveryapp.features.favorites.domain.usecase

import com.example.fooddeliveryapp.features.favorites.domain.repository.FavoritesDataRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoritesDataRepository: FavoritesDataRepository){
    suspend operator fun invoke(store:String,userId:String) = favoritesDataRepository.getFavorites(store,userId)
}