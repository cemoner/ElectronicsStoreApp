package com.example.electronicsstoreapp.main.util

class StoreNameSingleton private constructor(){
    companion object {
        private var storeName:String = "canerture"

        fun getStoreName():String {
            return storeName
        }

    }
}