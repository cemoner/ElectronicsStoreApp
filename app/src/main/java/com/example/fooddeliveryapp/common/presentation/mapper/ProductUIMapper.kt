package com.example.fooddeliveryapp.common.presentation.mapper

import com.example.fooddeliveryapp.common.domain.model.entity.Product
import com.example.fooddeliveryapp.common.presentation.model.entity.ProductUI

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