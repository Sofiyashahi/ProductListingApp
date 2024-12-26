package com.example.productlist.api

import android.util.Log
import com.example.productlist.model.Product

class ServerLogger {

    companion object {

        suspend fun fetchProductList(): ArrayList<Product>? {
            val response = RetrofitInstance.api.getProductList()
            if(response.isSuccessful) {
                Log.d("ProductList", "getProductList: ${response.body()}")
                return response.body()?.products
            }
            else return null
        }
    }
}