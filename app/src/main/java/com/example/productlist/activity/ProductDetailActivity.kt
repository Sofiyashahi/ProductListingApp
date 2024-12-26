package com.example.productlist.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.productlist.AppUtil
import com.example.productlist.R
import com.example.productlist.api.ServerLogger
import com.example.productlist.databinding.ActivityProductDetailBinding
import com.example.productlist.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var position = 0
    private var list = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

    }

    private fun setUpView(){
        position = intent.getIntExtra("position", 0)
        binding.btBack.setOnClickListener {
            onBackPressed()
        }

        showQuantity()
        cartItem()

        CoroutineScope(Dispatchers.IO).launch {
            list = ServerLogger.fetchProductList()!!

            withContext(Dispatchers.Main) {
                val title = list[position].title
                val price = list[position].price
                val discount = list[position].discount
                val description = list[position].description
                val ratings = list[position].rating
                val brand = list[position].brand
                val warranty = list[position].warranty
                val shipping = list[position].shippingInfo
                val availableStatus = list[position].availabilityStatus
                val imageUri = list[position].image

                Glide.with(this@ProductDetailActivity)
                    .load(imageUri)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.image)

                binding.tvTitle.text = title
                binding.tvPrice.text = price.toString()+" Rs"
                binding.tvDiscount.text = discount.toString()+"% off"
                binding.tvDescription.text = description
                binding.tvRatings.text = "Ratings: ${ratings.toString()}"
                binding.tvBrand.text = brand
                binding.tvWarranty.text = warranty
                binding.tvShipping.text = shipping
                binding.tvAvailability.text = availableStatus

                if(availableStatus == "In Stock")
                    binding.tvAvailability.setTextColor(ContextCompat.getColor(this@ProductDetailActivity, R.color.green))
                else binding.tvAvailability.setTextColor(ContextCompat.getColor(this@ProductDetailActivity, R.color.red))
            }
        }
    }

    private fun showQuantity(){
        if(AppUtil.totalQuantity > 0){
            binding.tvQuantity.visibility = View.VISIBLE
            binding.tvQuantity.text = AppUtil.totalQuantity.toString()

        }
    }

    private fun cartItem(){
        binding.cart.setOnClickListener {
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}