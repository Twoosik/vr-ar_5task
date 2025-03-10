package com.example.vr_ar_5task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products = emptyList<Product>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.title
        holder.price.text = "$${product.price}"
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun submitList(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}