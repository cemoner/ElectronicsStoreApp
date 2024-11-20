package com.example.fooddeliveryapp.common.data.mapper

import com.example.fooddeliveryapp.common.data.model.entity.ProductDto
import com.example.fooddeliveryapp.common.domain.model.entity.Product

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