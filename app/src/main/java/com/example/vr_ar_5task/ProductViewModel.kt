package com.example.vr_ar_5task

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(
        AppDatabase.getDatabase(application).productDao()
    )

    val products: Flow<List<Product>> = repository.getProductsFlow()

    fun refresh() {
        viewModelScope.launch {
            repository.refreshProducts()
        }
    }
}