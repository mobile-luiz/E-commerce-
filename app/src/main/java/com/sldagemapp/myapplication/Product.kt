package com.sldagemapp.myapplication

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val originalPrice: Double? = null,
    val discount: Int? = null,
    val category: String,
    val imageUrl: String,
    val rating: Double,
    val reviewCount: Int,
    val inStock: Boolean = true,
    val tags: List<String> = emptyList(),
    val colors: List<String> = emptyList(),
    val sizes: List<String> = emptyList(),
    val brand: String? = null,
    val weight: Double? = null,
    val dimensions: String? = null,
    val sku: String = "",
    val warranty: String = "12 meses"
)