package com.example.vr_ar_5task

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var items = emptyList<Product>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val price: TextView = view.findViewById(R.id.tvPrice)
        val brand: TextView = view.findViewById(R.id.tvBrand) // Добавьте TextView для brand
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = items[position]
        holder.title.text = product.title
        holder.price.text = "$${product.price}"

        // Обработка null для brand
        val brandText = product.brand ?: "No Brand"
        holder.brand.text = "Brand: $brandText"
    }

    override fun getItemCount() = items.size

    fun submitList(newItems: List<Product>) {
        items = newItems
        notifyDataSetChanged()
    }
}