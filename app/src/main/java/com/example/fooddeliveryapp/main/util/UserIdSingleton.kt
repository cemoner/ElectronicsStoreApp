package com.example.fooddeliveryapp.main.util
class UserIdSingleton private constructor(){
    companion object {
        private var userId:String = "-1"

        fun getUserId():String {
            return userId
        }

        fun setUserId(name: String) {
            userId = name
        }
    }
}