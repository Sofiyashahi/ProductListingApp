package com.example.productlist.api

import com.example.productlist.model.ProductModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProductList(): Response<ProductModel>
}