package com.example.productlist.adapter

import android.content.Context
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
import com.example.productlist.R
import com.example.productlist.model.CartItem

class CartItemAdapter(private val context: Context, private val cartItem: ArrayList<CartItem>): RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val image: ImageView = itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cartItem[position]

        holder.tvTitle.text = item.title
        holder.tvPrice.text = item.price.toString()
        holder.tvQuantity.text = "Unit: ${item.quantity}"

        Glide.with(context)
            .load(item.image)
            .placeholder(R.drawable.placeholder)
            .into(holder.image)
    }
}