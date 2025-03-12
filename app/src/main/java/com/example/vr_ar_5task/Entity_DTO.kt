package com.example.vr_ar_5task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    @ColumnInfo(name = "discount_percent") val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String?, // Разрешаем brand быть null
    val category: String,
    val thumbnail: String
) {
    companion object {
        fun fromNetwork(networkProduct: NetworkProduct) = Product(
            id = networkProduct.id,
            title = networkProduct.title,
            description = networkProduct.description,
            price = networkProduct.price,
            discountPercentage = networkProduct.discountPercentage,
            rating = networkProduct.rating,
            stock = networkProduct.stock,
            brand = networkProduct.brand ?: "Ноунейм", // Замените "Unknown" на значение по умолчанию
            category = networkProduct.category,
            thumbnail = networkProduct.thumbnail
        )
    }
}

data class NetworkProduct(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String
)

data class ProductResponse(val products: List<NetworkProduct>)