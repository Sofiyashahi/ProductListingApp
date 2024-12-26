package com.example.productlist.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("products") var products: ArrayList<Product> = arrayListOf()
)
data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Float,
    @SerializedName("thumbnail") val image: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("discountPercentage") val discount: Float,
    @SerializedName("rating") val rating: Float,
    @SerializedName("brand") val brand: String,
    @SerializedName("warrantyInformation") val warranty: String,
    @SerializedName("shippingInformation") val shippingInfo: String,
    @SerializedName("availabilityStatus") val availabilityStatus: String,
    var itemQuantity: Int = 0

)