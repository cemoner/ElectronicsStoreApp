package com.example.fooddeliveryapp.authentication.login.domain.model

class IsLoggedInSingleton {
    companion object {
        private var isLoggedIn:Boolean = false

        fun getIsLoggedIn():Boolean {
            return isLoggedIn
        }

        fun setIsLoggedIn(boolean: Boolean) {
             isLoggedIn = boolean
        }
    }
}