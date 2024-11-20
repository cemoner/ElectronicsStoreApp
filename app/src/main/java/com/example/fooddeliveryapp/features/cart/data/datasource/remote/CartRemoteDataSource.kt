package com.example.fooddeliveryapp.features.cart.data.datasource.remote

import com.example.fooddeliveryapp.features.cart.data.api.CartApi
import com.example.fooddeliveryapp.features.cart.data.model.request.ClearCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.GetCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.response.CartResponse
import com.example.fooddeliveryapp.features.cart.data.model.response.ClearCartResponse
import com.example.fooddeliveryapp.features.cart.data.model.response.DeleteFromCartResponse
import retrofit2.Response
import javax.inject.Inject

class CartRemoteDataSource @Inject constructor(private val cartApi: CartApi) {

    suspend fun getCart(store:String, getCartRequest: GetCartRequest): Response<CartResponse> = cartApi.getCart(store, getCartRequest.userId)

    suspend fun deleteFromCart(store: String,deleteFromCartRequest: DeleteFromCartRequest): Response<DeleteFromCartResponse> = cartApi.deleteFromCart(store,deleteFromCartRequest)

    suspend fun clearCart(store: String,clearCartRequest: ClearCartRequest): Response<ClearCartResponse> = cartApi.clearCart(store, clearCartRequest)
}