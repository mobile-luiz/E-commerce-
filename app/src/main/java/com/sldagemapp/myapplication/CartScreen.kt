package com.sldagemapp.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: EcommerceViewModel,
    navController: NavController
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val total = viewModel.getCartTotal()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrinho de Compras") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total:",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "R$ ${String.format("%.2f", total)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* TODO: Checkout */ },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = cartItems.isNotEmpty()
                    ) {
                        Text(
                            text = "Finalizar Compra",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seu carrinho está vazio",
                        style = MaterialTheme.typography.titleLarge,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Adicione produtos ao carrinho",
                        style = MaterialTheme.typography.bodyLarge,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cartItems, key = { it.product.id }) { cartItem ->
                    CartItemCard(
                        cartItem = cartItem,
                        onRemove = { viewModel.removeFromCart(cartItem) },
                        onQuantityChange = { newQuantity ->
                            viewModel.updateQuantity(cartItem, newQuantity)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onRemove: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Product Image
            AsyncImage(
                model = cartItem.product.imageUrl,
                contentDescription = cartItem.product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Product Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cartItem.product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "R$ ${String.format("%.2f", cartItem.product.price)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Quantity Controls
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { onQuantityChange(cartItem.quantity - 1) },
                        modifier = Modifier.size(32.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("-", fontWeight = FontWeight.Bold)
                    }

                    Text(
                        text = cartItem.quantity.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedButton(
                        onClick = { onQuantityChange(cartItem.quantity + 1) },
                        modifier = Modifier.size(32.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("+", fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Remove Button
            IconButton(onClick = onRemove) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Remover",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}