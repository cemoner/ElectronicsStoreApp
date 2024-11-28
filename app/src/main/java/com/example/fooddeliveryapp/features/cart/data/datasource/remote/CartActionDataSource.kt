package com.example.fooddeliveryapp.features.cart.data.datasource.remote

import com.example.fooddeliveryapp.features.cart.data.api.CartActionApi
import com.example.fooddeliveryapp.features.cart.data.model.request.ClearCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.GetCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.response.GetCartResponse
import com.example.fooddeliveryapp.features.cart.data.model.response.ClearCartResponse
import com.example.fooddeliveryapp.features.cart.data.model.response.DeleteFromCartResponse
import retrofit2.Response
import javax.inject.Inject

class CartActionDataSource @Inject constructor(private val cartApi: CartActionApi) {

    suspend fun getCart(store:String, getCartRequest: GetCartRequest): Response<GetCartResponse> = cartApi.getCart(store, getCartRequest.userId)

    suspend fun deleteFromCart(store: String,deleteFromCartRequest: DeleteFromCartRequest): Response<DeleteFromCartResponse> = cartApi.deleteFromCart(store,deleteFromCartRequest)

    suspend fun clearCart(store: String,clearCartRequest: ClearCartRequest): Response<ClearCartResponse> = cartApi.clearCart(store, clearCartRequest)
}