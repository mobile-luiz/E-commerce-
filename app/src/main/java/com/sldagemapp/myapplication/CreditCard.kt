package com.sldagemapp.myapplication

data class CreditCard(
    val cardNumber: String,
    val cardHolder: String,
    val expiryDate: String,
    val cvv: String,
    val installments: Int = 1,
    val cardType: String = "credit"
)