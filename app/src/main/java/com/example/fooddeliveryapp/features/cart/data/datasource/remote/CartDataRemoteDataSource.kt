package com.example.fooddeliveryapp.features.cart.data.datasource.remote

import com.example.fooddeliveryapp.common.data.model.response.MultipleProductsResponse
import com.example.fooddeliveryapp.features.cart.data.api.CartDataApi
import com.example.fooddeliveryapp.features.cart.data.model.request.GetCartRequest
import retrofit2.Response
import javax.inject.Inject

class CartDataRemoteDataSource @Inject constructor(private val cartDataApi: CartDataApi) {
    suspend fun getCart(store:String, getCartRequest: GetCartRequest): Response<MultipleProductsResponse> = cartDataApi.getCart(store, getCartRequest.userId)
}