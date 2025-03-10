package com.example.vr_ar_5task

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter()
        recyclerView.adapter = adapter

        // Загружаем данные
        viewModel.fetchProducts()

        // Наблюдаем за изменениями в данных
        viewModel.products.observe(this, { products ->
            products?.let { adapter.submitList(it) }
        })
    }
}