package com.sldagemapp.myapplication

data class PaymentResult(
    val success: Boolean,
    val orderId: String? = null,
    val message: String,
    val paymentMethod: String,
    val transactionId: String? = null
)