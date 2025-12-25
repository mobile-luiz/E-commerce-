package com.sldagemapp.myapplication

data class Coupon(
    val code: String,
    val discountType: String, // "percentage" or "fixed"
    val value: Double,
    val minPurchase: Double = 0.0,
    val validUntil: String? = null,
    val isActive: Boolean = true
)