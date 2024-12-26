package com.example.productlist.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productlist.AppUtil
import com.example.productlist.R
import com.example.productlist.adapter.CartItemAdapter
import com.example.productlist.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartItemAdapter
    private var cartItemList = AppUtil.cartItemList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

    }

    private fun setUpView(){
        binding.btBack.setOnClickListener {
            onBackPressed()
        }

        if(cartItemList.isEmpty()){
            binding.tvEmptyCart.visibility = View.VISIBLE
            binding.rvCartItems.visibility = View.GONE
        } else {
            binding.tvEmptyCart.visibility = View.GONE
            binding.rvCartItems.visibility = View.VISIBLE
            binding.rvCartItems.layoutManager = LinearLayoutManager(this)
            adapter = CartItemAdapter(this, cartItemList)
            binding.rvCartItems.adapter = adapter
        }
    }
}