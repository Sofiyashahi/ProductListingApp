package com.example.productlist.interfaces

import com.example.productlist.model.Product

interface ProductItemClickListener {

    fun onItemClick(position: Int)
}

interface PlusMinusClickListener {

    fun onPlusClick()
    fun onMinusClick()

}