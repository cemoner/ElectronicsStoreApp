package com.example.fooddeliveryapp.main.util

import com.example.fooddeliveryapp.common.presentation.model.ProductUI

class FavoritesSingleton private constructor(){
    companion object {
        private var favorites: List<ProductUI> = emptyList()
        fun getFavorites(): List<ProductUI> {
            return favorites
        }
        fun setFavorites(newFavorites: List<ProductUI>) {
            favorites = newFavorites
        }
    }
}