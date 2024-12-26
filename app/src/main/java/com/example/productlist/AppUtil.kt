package com.example.productlist

import android.app.Application
import com.example.productlist.model.CartItem

class AppUtil : Application(){

    companion object {
        var totalQuantity = 0
        var cartItemList = ArrayList<CartItem>()
    }
}