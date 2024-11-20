package com.example.fooddeliveryapp.features.home.data.mapper

import com.example.fooddeliveryapp.features.home.data.model.entity.ProductDto
import com.example.fooddeliveryapp.features.home.domain.model.Product

fun ProductDto.toDomainModel(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        salePrice = salePrice,
        description = description,
        category = category,
        imageOne = imageOne,
        imageTwo = imageTwo,
        imageThree = imageThree,
        rate = rate,
        count = count, saleState = saleState
    )
}