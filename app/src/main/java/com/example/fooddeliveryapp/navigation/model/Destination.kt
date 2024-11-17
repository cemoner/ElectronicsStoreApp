package com.example.fooddeliveryapp.navigation.model

import android.net.Uri
import android.util.Log
import com.example.fooddeliveryapp.home.presentation.model.ProductUI

sealed class Destination(protected val route:String,vararg params:String) {

    val fullRoute:String = if(params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentDestination(route: String): Destination(route){
        operator fun invoke():String = route
    }


    object Home: NoArgumentDestination("home")
    object Favorites: NoArgumentDestination("favorites")
    object Login: NoArgumentDestination("login")
    object Register: NoArgumentDestination("register")
    object Cart: NoArgumentDestination("cart")


    object ProductDetail : Destination(
        route = "productDetail",
        "id", "title", "price", "salePrice", "description", "category", "image", "rate"
    ) {
        const val ID_KEY = "id"
        const val TITLE_KEY = "title"
        const val PRICE_KEY = "price"
        const val SALE_PRICE_KEY = "salePrice"
        const val DESCRIPTION_KEY = "description"
        const val CATEGORY_KEY = "category"
        const val IMAGE_KEY = "image"
        const val RATE_KEY = "rate"

        operator fun invoke(product: ProductUI): String = route.appendParams(
            ID_KEY to product.id,
            TITLE_KEY to Uri.encode(product.title),
            PRICE_KEY to product.price,
            SALE_PRICE_KEY to product.salePrice,
            DESCRIPTION_KEY to Uri.encode(product.description),
            CATEGORY_KEY to Uri.encode(product.category),
            IMAGE_KEY to Uri.encode(product.image),
            RATE_KEY to product.rate
        )
    }

    object Profile: Destination("profile","userId"){
        const val USER_ID_KEY = "userId"

        operator fun invoke(userId:String):String = route.appendParams(
            USER_ID_KEY to userId
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