package com.example.fooddeliveryapp.retrofit

import android.util.Log
import com.example.fooddeliveryapp.authentication.login.data.model.result.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

interface ApiHandler {
    suspend fun <T: Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()


            if (response.isSuccessful) {
                NetworkResult.Success(response.code(), response.body()!!)
            } else {
                Log.d("APIHandler", "Error body: ${response.errorBody()?.string()}")
                NetworkResult.Error(response.code(), response.errorBody()?.string())
            }
        } catch (e: HttpException) {
            Log.d("APIHandler", "HTTP Exception: ${e.message()}")
            NetworkResult.Error(e.code(), e.message())
        } catch (e: Throwable) {
            Log.d("APIHandler", "Unknown Exception: ${e.message}")
            NetworkResult.Exception(e)
        }
    }
}