package com.example.productlist.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlist.AppUtil
import com.example.productlist.AppUtil.Companion.cartItemList
import com.example.productlist.R
import com.example.productlist.interfaces.PlusMinusClickListener
import com.example.productlist.interfaces.ProductItemClickListener
import com.example.productlist.model.CartItem
import com.example.productlist.model.Product

class ProductListAdapter(
    private val productList: ArrayList<Product>, private val context: Context,
    private val productClickListener: ProductItemClickListener,
    private val plusMinusClickListener: PlusMinusClickListener
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val image: ImageView = itemView.findViewById(R.id.image)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val btCart: AppCompatButton = itemView.findViewById(R.id.btAddToCart)
        val layoutPlusMinus: LinearLayout = itemView.findViewById(R.id.plus_minus_layout)
        val btPlus: AppCompatButton = itemView.findViewById(R.id.btPlus)
        val btMinus: AppCompatButton = itemView.findViewById(R.id.btMinus)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = productList[position]

        holder.tvTitle.text = items.title
        holder.tvPrice.text = items.price.toString()
        holder.tvCategory.text = "Category: ${items.category}"

        Glide.with(context)
            .load(items.image)
            .placeholder(R.drawable.placeholder)
            .into(holder.image)

        holder.cardView.setOnClickListener {
            productClickListener.onItemClick(position)
        }

        holder.btCart.setOnClickListener {
            holder.btCart.visibility = View.GONE
            holder.layoutPlusMinus.visibility = View.VISIBLE
            AppUtil.totalQuantity++
            items.itemQuantity += 1
            holder.tvQuantity.text = items.itemQuantity.toString()
            plusMinusClickListener.onPlusClick()
            cartItemList.add(CartItem(items.image, items.title, items.price, items.itemQuantity))
        }

        holder.btPlus.setOnClickListener {
            AppUtil.totalQuantity++
            items.itemQuantity += 1
            holder.tvQuantity.text = items.itemQuantity.toString()
            plusMinusClickListener.onPlusClick()
            Log.d("QuantityCheck", "onBindViewHolder: item quantity-> ${items.itemQuantity}")
            updateQuantity(items, "+")
        }

        holder.btMinus.setOnClickListener {
            AppUtil.totalQuantity--
            if (items.itemQuantity > 1) {
                items.itemQuantity -= 1
                holder.tvQuantity.text = items.itemQuantity.toString()
                updateQuantity(items, "-")
            } else {
                holder.layoutPlusMinus.visibility = View.GONE
                holder.btCart.visibility = View.VISIBLE
                cartItemList.removeIf { it.title == items.title }
            }
            plusMinusClickListener.onMinusClick()
        }

    }

    private fun updateQuantity(item: Product, operation: String){
        val findPosition = cartItemList.indexOfFirst {it.title == item.title}
        if(operation == "+")
            cartItemList[findPosition].quantity += 1
        else
            cartItemList[findPosition].quantity -= 1

    }
}