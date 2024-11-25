package com.example.electronicsstoreapp.main.util

import androidx.compose.runtime.mutableStateListOf

object FavoritesSingleton {
    // Mutable state list to track favorites
    private val _favorites = mutableStateListOf<Int>()

    // Exposed as an immutable list
    val favorites: List<Int>
        get() = _favorites

    fun addToFavorites(productId: Int) {
        if (!favorites.contains(productId)) {
            _favorites.add(productId)
        }
    }

    fun deleteFromFavorites(productId: Int) {
        _favorites.remove(productId)
    }

    fun isFavorite(productId: Int): Boolean = favorites.contains(productId)

    fun clearFavorites() {
        _favorites.clear()
    }
}
