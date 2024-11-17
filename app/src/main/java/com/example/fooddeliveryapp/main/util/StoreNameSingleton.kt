package com.example.fooddeliveryapp.main.util

class StoreNameSingleton private constructor(){
    companion object {
        private var storeName:String = "canerture"

        fun getStoreName():String {
            return storeName
        }

        fun setStoreName(name: String) {
            storeName = name
        }
    }
}