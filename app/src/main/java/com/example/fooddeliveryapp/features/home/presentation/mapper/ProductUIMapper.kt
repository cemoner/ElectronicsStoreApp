package com.example.fooddeliveryapp.features.home.presentation.mapper

import com.example.fooddeliveryapp.features.home.domain.model.Product
import com.example.fooddeliveryapp.features.home.presentation.model.ProductUI

fun Product.toUiModel(): ProductUI {
    return ProductUI(
        id = id,
        title = title,
        price = price,
        salePrice = salePrice,
        description = description,
        category = category,
        image = imageOne,
        rate = rate,
    )
}