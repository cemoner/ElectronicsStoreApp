package com.example.fooddeliveryapp.features.cart.data.datasource.remote

import com.example.fooddeliveryapp.features.cart.data.api.CartActionApi
import com.example.fooddeliveryapp.features.cart.data.model.request.ClearCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.response.ClearCartResponse
import com.example.fooddeliveryapp.features.cart.data.model.response.DeleteFromCartResponse
import retrofit2.Response
import javax.inject.Inject

class CartActionRemoteDataSource @Inject constructor(private val cartActionApi: CartActionApi) {


    suspend fun deleteFromCart(store: String,deleteFromCartRequest: DeleteFromCartRequest): Response<DeleteFromCartResponse> = cartActionApi.deleteFromCart(store,deleteFromCartRequest)

    suspend fun clearCart(store: String,clearCartRequest: ClearCartRequest): Response<ClearCartResponse> = cartActionApi.clearCart(store, clearCartRequest)
}