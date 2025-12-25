package com.sldagemapp.myapplication

data class PaymentMethod(
    val id: String,
    val name: String,
    val iconName: String,
    val description: String,
    val isAvailable: Boolean = true,
    val maxInstallments: Int = 1,
    val fee: Double = 0.0
)