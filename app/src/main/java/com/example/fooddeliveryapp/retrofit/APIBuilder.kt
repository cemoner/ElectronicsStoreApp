package com.example.fooddeliveryapp.retrofit

import com.example.fooddeliveryapp.retrofit.RetrofitClient.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://virtserver.swaggerhub.com/candroid/E-Commerce/1.0.0"

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