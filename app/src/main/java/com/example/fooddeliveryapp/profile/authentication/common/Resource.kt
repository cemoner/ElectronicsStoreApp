package com.example.fooddeliveryapp.profile.authentication.common

sealed class Resource<out T> {
    data class Success<out T>(val data:ArrayList<String?>): Resource<T>()
    data class Error(val exception:Exception): Resource<Nothing>()
}