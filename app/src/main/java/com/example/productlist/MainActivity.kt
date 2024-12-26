package com.example.productlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productlist.activity.CartActivity
import com.example.productlist.activity.ProductDetailActivity
import com.example.productlist.adapter.ProductListAdapter
import com.example.productlist.api.ServerLogger
import com.example.productlist.databinding.ActivityMainBinding
import com.example.productlist.interfaces.PlusMinusClickListener
import com.example.productlist.interfaces.ProductItemClickListener
import com.example.productlist.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), ProductItemClickListener, PlusMinusClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductListAdapter
    private var productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        getProductList()

        binding.cart.setOnClickListener{
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun getProductList() {
        CoroutineScope(Dispatchers.IO).launch {
            productList = ServerLogger.fetchProductList()!!

            withContext(Dispatchers.Main) {
                adapter = ProductListAdapter(productList, this@MainActivity, this@MainActivity, this@MainActivity)
                binding.rvProducts.adapter = adapter
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onItemClick(position: Int) {
        Intent(this, ProductDetailActivity::class.java).also {
            it.putExtra("position", position)
            startActivity(it)
        }
    }

    override fun onPlusClick() {
        if(!binding.tvQuantity.isVisible){
            binding.tvQuantity.visibility = View.VISIBLE
        }
        binding.tvQuantity.text = AppUtil.totalQuantity.toString()
    }

    override fun onMinusClick() {
        if(AppUtil.totalQuantity == 0){
            binding.tvQuantity.visibility = View.GONE
        } else {
            binding.tvQuantity.text = AppUtil.totalQuantity.toString()
        }
    }
}