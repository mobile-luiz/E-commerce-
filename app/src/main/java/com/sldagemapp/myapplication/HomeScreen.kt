package com.sldagemapp.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: EcommerceViewModel,
    navController: NavController
) {
    val products by viewModel.products.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { Text("Loja Online") },
            actions = {
                IconButton(onClick = { navController.navigate("cart") }) {
                    BadgedBox(
                        badge = {
                            if (cartItems.isNotEmpty()) {
                                Badge {
                                    Text(cartItems.sumOf { it.quantity }.toString())
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho")
                    }
                }
            }
        )

        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Buscar produtos...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            shape = MaterialTheme.shapes.large
        )

        // Categories
        val categories = listOf("Todos", "Eletrônicos", "Vestuário", "Casa", "Esportes")
        var selectedCategory by remember { mutableStateOf("Todos") }

        ScrollableTabRow(
            selectedTabIndex = categories.indexOf(selectedCategory),
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                Tab(
                    selected = category == selectedCategory,
                    onClick = { selectedCategory = category },
                    text = { Text(category) }
                )
            }
        }

        // Product Grid
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = products.filter {
                    selectedCategory == "Todos" || it.category == selectedCategory
                },
                key = { it.id }
            ) { product ->
                ProductCard(
                    product = product,
                    onProductClick = {
                        viewModel.selectProduct(product)
                        navController.navigate("product/${product.id}")
                    },
                    onAddToCart = { viewModel.addToCart(product) }
                )
            }
        }
    }
}