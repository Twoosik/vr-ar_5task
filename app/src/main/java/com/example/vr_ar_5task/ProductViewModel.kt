package com.example.vr_ar_5task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getAllProducts()
                _products.value = response.products
            } catch (e: Exception) {
                // Обработка ошибок (можно оставить для отладки в будущем)
                e.printStackTrace()
            }
        }
    }
}