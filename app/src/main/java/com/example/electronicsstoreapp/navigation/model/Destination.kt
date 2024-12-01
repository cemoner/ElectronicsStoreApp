package com.example.electronicsstoreapp.navigation.model

import com.example.electronicsstoreapp.features.home.presentation.model.CarouselItem
import com.example.electronicsstoreapp.main.util.GsonUtils.gson

sealed class Destination(
    protected val route: String,
    vararg params: String,
) {
    val fullRoute: String =
        if (params.isEmpty()) {
            route
        } else {
            val builder = StringBuilder(route)
            params.forEach { builder.append("/{$it}") }
            builder.toString()
        }

    sealed class NoArgumentDestination(
        route: String,
    ) : Destination(route) {
        operator fun invoke(): String = route
    }

    object Home : NoArgumentDestination("home")

    object Favorites : NoArgumentDestination("favorites")

    object Login : NoArgumentDestination("login")

    object Register : NoArgumentDestination("register")

    object Cart : NoArgumentDestination("cart")

    object ProductDetail : Destination(
        route = "productDetail",
        "productId","carouselItems"
    ) {
        const val PRODUCT_ID_KEY = "productId"

        const val CAROUSEL_ITEMS_KEY = "carouselItems"


        operator fun invoke(productId: Int,carouselItems:List<CarouselItem>): String {
            val serializedCarouselItems = gson.toJson(carouselItems)

            val encodedCarouselItems = java.net.URLEncoder.encode(serializedCarouselItems, "UTF-8")

           return route.appendParams(
                PRODUCT_ID_KEY to productId,
                CAROUSEL_ITEMS_KEY to encodedCarouselItems,
            )
        }
    }

    data object Profile : Destination("profile", "userId") {
        const val USER_ID_KEY = "userId"

        operator fun invoke(userId: String): String =
            route.appendParams(
                USER_ID_KEY to userId,
            )
    }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}
