package com.example.electronicsstoreapp.common.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val salePrice: Double,
    val description: String,
    val category: String,
    val imageOne: String,
    val imageTwo: String,
    val imageThree: String,
    val rate: Double,
    val count: Int,
    val saleState: Boolean,
)
