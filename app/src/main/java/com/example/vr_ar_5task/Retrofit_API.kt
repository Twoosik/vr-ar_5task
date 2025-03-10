package com.example.vr_ar_5task

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getAllProducts(): ProductResponse
}

data class ProductResponse(
    val products: List<Product>
)

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