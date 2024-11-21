package com.example.electronicsstoreapp.common.presentation.mapper

import com.example.electronicsstoreapp.common.domain.model.Product
import com.example.electronicsstoreapp.common.presentation.model.ProductUI

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