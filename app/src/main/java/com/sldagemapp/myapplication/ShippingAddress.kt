package com.sldagemapp.myapplication

import java.util.UUID

data class ShippingAddress(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val street: String,
    val number: String,
    val complement: String? = null,
    val neighborhood: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val phone: String,
    val isDefault: Boolean = false
)