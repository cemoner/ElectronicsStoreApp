package com.example.electronicsstoreapp.common.presentation.mapper

import com.example.electronicsstoreapp.common.domain.model.Product
import com.example.electronicsstoreapp.common.presentation.model.ProductUI

fun Product.toUiModel(): ProductUI =
    ProductUI(
        id = id,
        title = title,
        price = price,
        salePrice = salePrice,
        description = description,
        category = category,
        image1 = imageOne,
        image2 = imageTwo,
        image3 = imageThree,
        rate = rate,
    )
