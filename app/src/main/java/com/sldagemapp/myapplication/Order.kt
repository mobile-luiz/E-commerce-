package com.sldagemapp.myapplication

data class Order(
    val id: String = "ORD-${System.currentTimeMillis()}",
    val items: List<CartItem>,
    val subtotal: Double,
    val shipping: Double,
    val discount: Double,
    val total: Double,
    val paymentMethod: String,
    val paymentDetails: String,
    val status: String,
    val date: String,
    val shippingAddress: ShippingAddress? = null,
    val trackingNumber: String? = null,
    val estimatedDelivery: String? = null
)