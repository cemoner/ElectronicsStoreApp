package com.example.electronicsstoreapp.navigation.model

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
        "productId") {
        const val PRODUCT_ID_KEY =  "productId"

        operator fun invoke(productId:Int): String = route.appendParams(
            PRODUCT_ID_KEY to productId,
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