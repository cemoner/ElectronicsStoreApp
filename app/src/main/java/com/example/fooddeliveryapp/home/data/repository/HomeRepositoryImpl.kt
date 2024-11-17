package com.example.fooddeliveryapp.home.data.repository

import com.example.fooddeliveryapp.home.data.datasource.remote.retrofit.RetrofitDataSource
import com.example.fooddeliveryapp.home.domain.model.Product
import com.example.fooddeliveryapp.home.domain.repository.HomeRepository
import com.example.fooddeliveryapp.retrofit.ApiHandler
import com.example.fooddeliveryapp.retrofit.NetworkResult
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: RetrofitDataSource): HomeRepository,
    ApiHandler {
    override suspend fun getProducts(store: String): List<Product> {
        val result = handleApi { remoteDataSource.getProducts(store) }
        when (result) {
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

    override suspend fun getSaleProducts(store: String): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsByCategory(
        store: String,
        category: String
    ): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun searchProduct(store: String, query: String): List<Product> {
        TODO("Not yet implemented")
    }

}