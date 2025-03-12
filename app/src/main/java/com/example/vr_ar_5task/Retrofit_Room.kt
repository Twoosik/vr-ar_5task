package com.example.vr_ar_5task

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Retrofit
interface ProductApi {
    @GET("products")
    suspend fun getAllProducts(): ProductResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/"
    val instance: ProductApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }
}

// Room
@Database(entities = [Product::class], version = 2) // Увеличьте версию
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java, "products.db"
            ).fallbackToDestructiveMigration() // Очищает старую базу данных
                .build().also { instance = it }
        }
    }
}