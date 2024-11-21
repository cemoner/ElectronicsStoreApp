package com.example.electronicsstoreapp.main.util

class IsLoggedInSingleton private constructor() {
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