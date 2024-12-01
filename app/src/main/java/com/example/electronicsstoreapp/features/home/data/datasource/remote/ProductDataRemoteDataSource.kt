package com.example.electronicsstoreapp.features.home.data.datasource.remote

import com.example.electronicsstoreapp.common.data.model.response.MultipleProductsResponse
import com.example.electronicsstoreapp.common.data.model.response.SingularProductResponse
import com.example.electronicsstoreapp.features.home.data.api.ProductDataApi
import retrofit2.Response
import javax.inject.Inject

class ProductDataRemoteDataSource
    @Inject
    constructor(
        private val productDataApi: ProductDataApi,
    ) {
        suspend fun getProducts(store: String): Response<MultipleProductsResponse> = productDataApi.getProducts(store)

        suspend fun getProductDetail(
            store: String,
            productId: Int,
        ): Response<SingularProductResponse> = productDataApi.getProductDetail(store, productId)

        suspend fun getFavorites(
            store: String,
            userId: String,
        ): Response<MultipleProductsResponse> = productDataApi.getFavorites(store, userId)

        suspend fun searchProduct(
            store: String,
            query: String,
        ): Response<MultipleProductsResponse> = productDataApi.searchProduct(store, query)
    }
