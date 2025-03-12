package com.example.vr_ar_5task

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>> // Возвращаем Flow<List<Product>>
}

class ProductRepository(private val dao: ProductDao) {
    private val api: ProductApi by lazy { RetrofitClient.instance }

    suspend fun refreshProducts() {
        try {
            val response = api.getAllProducts()
            Log.d("ProductRepository", "Data received: ${response.products.size} items")
            Log.d("ProductRepository", "First product: ${response.products.firstOrNull()}")

            val products = response.products.map { Product.fromNetwork(it) }
            dao.insertAll(products)
            Log.d("ProductRepository", "Data inserted into database")
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error: ${e.message}")
        }
    }

    fun getProductsFlow(): Flow<List<Product>> = dao.getAllProducts()
}