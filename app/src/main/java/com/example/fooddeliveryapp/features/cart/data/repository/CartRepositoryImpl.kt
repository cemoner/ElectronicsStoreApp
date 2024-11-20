package com.example.fooddeliveryapp.features.cart.data.repository

import com.example.fooddeliveryapp.features.cart.data.datasource.remote.CartRemoteDataSource
import com.example.fooddeliveryapp.features.cart.data.model.request.ClearCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.DeleteFromCartRequest
import com.example.fooddeliveryapp.features.cart.data.model.request.GetCartRequest
import com.example.fooddeliveryapp.features.cart.domain.model.Product
import com.example.fooddeliveryapp.features.cart.domain.repository.CartRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val retrofitDataSource: CartRemoteDataSource):
    CartRepository,ApiHandler {
    override suspend fun getCart(store: String, userId:String): List<Product> {
        val result = handleApi { retrofitDataSource.getCart(store, GetCartRequest(userId)) }

        when(result){
            is NetworkResult.Success -> {
                val products = result.data
                val productList = mutableListOf<Product>()
                for (product in products.products) {
                    val productTransferObject = Product(
                            id = product.id,
                            title = product.title,
                            price = product.price,
                            salePrice = product.salePrice,
                            description = product.description,
                            category = product.category,
                            imageOne = product.imageOne,
                            imageTwo = product.imageTwo,
                            imageThree = product.imageThree,
                            rate = product.rate,
                            count = product.count,
                            saleState = product.saleState
                        )
                    productList.add(productTransferObject)
                }
                return productList
            }
            is NetworkResult.Error -> {
                return emptyList()
            }
            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }

    override suspend fun deleteFromCart(
        store: String,
        userId:String,
        productId:Int
    ): Pair<Int,String> {
        val result = handleApi { retrofitDataSource.deleteFromCart(store,
            DeleteFromCartRequest(userId,productId)
        ) }

         when (result) {
            is NetworkResult.Success -> {
                return Pair(result.data.status,result.data.message)
            }

            is NetworkResult.Error -> {
                if(result.errorMsg != null){
                    return Pair(result.code,result.errorMsg)
                }
                else {
                    return Pair(result.code,"Message couldn't be retrieved")
                }
            }

            is NetworkResult.Exception -> {
                throw result.e
            }
        }
    }

    override suspend fun clearCart(store: String, userId: String): Int {
        val result = handleApi { retrofitDataSource.clearCart(store, ClearCartRequest(userId))}

        return when (result) {
            is NetworkResult.Success -> {
                result.data.status
            }

            is NetworkResult.Error -> {
                result.code
            }

            is NetworkResult.Exception -> {
                throw result.e
            }

        }
    }
}