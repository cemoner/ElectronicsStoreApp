package com.example.electronicsstoreapp.features.home.presentation.mapper

import com.example.electronicsstoreapp.common.presentation.model.ProductUI
import com.example.electronicsstoreapp.features.home.presentation.model.CarouselItem

fun ProductUI.toCarouselItem():CarouselItem =
    CarouselItem (
        id = id,
        image = image1,
        title = title,
        category = category
    )