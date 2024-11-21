package com.example.electronicsstoreapp.retrofit

import com.example.electronicsstoreapp.retrofit.RetrofitClient.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.canerture.com/ecommerce/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    inline fun <reified T : API> create(): T {
        return retrofit.create(T::class.java)
    }
}