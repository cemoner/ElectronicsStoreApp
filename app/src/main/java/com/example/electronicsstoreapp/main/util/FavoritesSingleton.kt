package com.example.electronicsstoreapp.main.util

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class FavoritesSingleton private constructor() {
    companion object {
        private val _favorites = mutableStateOf(SnapshotStateList<Int>())
        val favorites: List<Int>
            get() = _favorites.value

        fun addToFavorites(productId: Int) {
            if (!favorites.contains(productId)) {
                _favorites.value.add(productId)
            }
        }

        fun deleteFromFavorites(productId: Int) {
            _favorites.value.remove(productId)
        }

        fun isFavorite(productId: Int): Boolean {
            return favorites.contains(productId)
        }
    }
}
