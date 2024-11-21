package com.example.electronicsstoreapp.common.presentation.model

data class ProductUI(
    val id: Int,
    val title: String,
    val price: Double,
    val salePrice: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
)
